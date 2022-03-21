package com.pix.controller;

import com.pix.dtos.UserPixDTO;
import com.pix.exceptions.DuplicateKeyException;
import com.pix.exceptions.InactivateKeyException;
import com.pix.exceptions.KeyNotFoundException;
import com.pix.service.UserPixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.pix.constants.Constants.*;

@RestController
@RequestMapping(PIX_ACTIONS)
@RequiredArgsConstructor
public class PixController {

    private final UserPixService userPixService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserPixDTO findByKey(@RequestParam String key) {
        return userPixService.findByKey(key);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserPixDTO createKey(@Validated @RequestBody UserPixDTO userPixDTO) throws DuplicateKeyException {
        return userPixService.create(userPixDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserPixDTO updateKey(@RequestBody UserPixDTO userPixDTO) throws KeyNotFoundException {
        return userPixService.update(userPixDTO);
    }

    @PutMapping(INACTIVATE)
    public UserPixDTO inactivateKey(@RequestParam UUID id) throws InactivateKeyException, KeyNotFoundException {
        return userPixService.delete(id);
    }

    @GetMapping(LIST_ALL_BY_TYPE_KEY)
    public List<UserPixDTO> findByTypeKey(@RequestParam String typeKey){
        return userPixService.findByTypeKey(typeKey);
    }
}
