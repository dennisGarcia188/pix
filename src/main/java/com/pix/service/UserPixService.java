package com.pix.service;

import com.pix.dtos.UserPixDTO;
import com.pix.exceptions.DuplicateKeyException;
import com.pix.exceptions.InactivateKeyException;
import com.pix.exceptions.KeyNotFoundException;

import java.util.List;
import java.util.UUID;

public interface UserPixService {

    UserPixDTO findByKey(String key) throws KeyNotFoundException;

    UserPixDTO create(UserPixDTO userPixDTO) throws DuplicateKeyException;

    UserPixDTO update(UserPixDTO userPixDTO) throws KeyNotFoundException;

    UserPixDTO delete(UUID id) throws InactivateKeyException, KeyNotFoundException;

    List<UserPixDTO> findByTypeKey(String typeKey);
}
