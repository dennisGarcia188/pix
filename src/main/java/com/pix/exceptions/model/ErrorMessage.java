package com.pix.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private int statusCode;
    private LocalDateTime timestamp;
    private String message;
    private String description;

}
