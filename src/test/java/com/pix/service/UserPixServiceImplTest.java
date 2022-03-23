package com.pix.service;

import com.pix.dtos.UserPixDTO;
import com.pix.exceptions.DuplicateKeyException;
import com.pix.exceptions.KeyNotFoundException;
import com.pix.mappers.UserPixMapper;
import com.pix.model.UserPixEntity;
import com.pix.repository.UserPixRepository;
import com.pix.service.impl.UserPixServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserPixServiceImplTest {

    @InjectMocks
    private UserPixServiceImpl userPixService;

    @Mock
    private UserPixRepository userPixRepository;

    @Mock
    private UserPixMapper mapper;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        userPixService = new UserPixServiceImpl(userPixRepository, mapper);
    }

    @Test
    @DisplayName("Deve criar uma chave do tipo CPF")
    public void shouldCreateKeyWithTypeCPF() throws DuplicateKeyException {
        UserPixEntity userPixEntity = mapper.fromDtoToEntity(buildUserPixDTO());
        when(userPixRepository.save((any(UserPixEntity.class)))).thenReturn(userPixEntity);
        UserPixDTO userPixDTO = userPixService.create(buildUserPixDTO());
        Assertions.assertNotNull(userPixDTO);
    }

    @Test
    @DisplayName("Deve atualizar um registro")
    public void shouldUpdateKey() throws KeyNotFoundException {
        //when(userPixService.update(buildUserPixDTO())).thenReturn();
    }

    public UserPixDTO buildUserPixDTO() {
        return UserPixDTO.builder()
                .keyInformation("36789734007")
                .agencyNumber(1234)
                .accountNumber(20406080)
                .holderName("Dennis")
                .holderLastName("Garcia")
                .insertTime(LocalDateTime.now())
                .build();
    }

    public UserPixEntity buildUserPixEntity() {
        return UserPixEntity.builder()
                .keyInformation("36789734007")
                .agencyNumber(1234)
                .accountNumber(20406080)
                .holderName("Dennis")
                .holderLastName("Garcia")
                .insertTime(LocalDateTime.now())
                .build();
    }


}
