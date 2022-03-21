package com.pix.service.impl;

import com.pix.dtos.UserPixDTO;
import com.pix.exceptions.DuplicateKeyException;
import com.pix.exceptions.InactivateKeyException;
import com.pix.exceptions.KeyNotFoundException;
import com.pix.exceptions.UpdatedInactiveKeyException;
import com.pix.mappers.UserPixMapper;
import com.pix.model.UserPixEntity;
import com.pix.repository.UserPixRepository;
import com.pix.service.UserPixService;
import com.pix.utils.VerifyTypeKeyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserPixServiceImpl implements UserPixService {

    private final UserPixRepository userPixRepository;
    private final UserPixMapper mapper;

    @Override
    public UserPixDTO findByKey(String key) throws KeyNotFoundException {
        UserPixEntity userPixEntity = userPixRepository.findByKeyInformation(key);
        if (null == userPixEntity) {
            throw new KeyNotFoundException("Não foi encontrado nenhum registro para essa chave");
        }
        return mapper.fromEntityToDTO(userPixEntity);
    }

    @Override
    public UserPixDTO create(UserPixDTO userPixDTO) throws DuplicateKeyException {
        try {
            userPixDTO.setInsertTime(LocalDateTime.now());
            userPixDTO.setTypeKey(VerifyTypeKeyUtils.fillType(userPixDTO.getKeyInformation()));
            return mapper.fromEntityToDTO(userPixRepository.save(mapper.fromDtoToEntity(userPixDTO)));
        } catch (Exception e) {
            throw new DuplicateKeyException("A chave já existe na base de dados", e.getMessage());
        }
    }

    @Override
    public UserPixDTO update(UserPixDTO userPixDTO) throws KeyNotFoundException {
        UserPixEntity userPixEntity = userPixRepository.findByKeyInformation(userPixDTO.getKeyInformation());
        if (verifyIfExist(userPixDTO.getKeyInformation())) {
            if (userPixEntity.getInactivateTime() != null) {
                throw new UpdatedInactiveKeyException("Uma chave inativa não pode ser atualizada");
            }
            userPixRepository.save(UserPixEntity.build(userPixDTO, userPixEntity));
            return mapper.fromEntityToDTO(userPixRepository.findByKeyInformation(userPixDTO.getKeyInformation()));
        } else {
            throw new KeyNotFoundException("A chave não existe");
        }
    }

    private Boolean verifyIfExist(String key) {
        UserPixEntity userPixEntity = userPixRepository.findByKeyInformation(key);
        return userPixEntity != null;
    }

    @Override
    public UserPixDTO delete(UUID id) throws KeyNotFoundException, InactivateKeyException {
        UserPixEntity userPixEntity = userPixRepository.getById(id);
        if (verifyStateKey(userPixEntity)) {
            if (verifyIfExist(userPixEntity.getKeyInformation())) {
                userPixEntity.setInactivateTime(LocalDateTime.now());
                return mapper.fromEntityToDTO(userPixRepository.save(UserPixEntity.build(mapper.fromEntityToDTO(userPixEntity), userPixEntity)));
            } else {
                throw new KeyNotFoundException("A chave não existe");
            }
        } else {
            throw new InactivateKeyException("A chave já foi inativada");
        }
    }

    @Override
    public List<UserPixDTO> findByTypeKey(String typeKey) {
        return mapper.fromEntityToDTOlist(userPixRepository.findByTypeKey(typeKey));
    }

    @Override
    public List<UserPixDTO> findByHolderName(String holderName) {
        return mapper.fromEntityToDTOlist(userPixRepository.findByHolderName(holderName));
    }

    private Boolean verifyStateKey(UserPixEntity userPixEntity) throws InactivateKeyException {
        if (userPixEntity.getInactivateTime() == null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
