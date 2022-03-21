package com.pix.mappers;

import com.pix.dtos.UserPixDTO;
import com.pix.model.UserPixEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserPixMapper {

    UserPixDTO fromEntityToDTO(UserPixEntity entity);

    UserPixEntity fromDtoToEntity(UserPixDTO dto);

    List<UserPixDTO> fromEntityToDTOlist(List<UserPixEntity> userPixEntityList);

}
