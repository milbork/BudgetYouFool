package com.budgetyoufool.exception.exceptions;

import com.budgetyoufool.model.DTO.TransactionDTO;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.time.LocalDateTime;

public class URIResponseException extends RuntimeException {
    public URIResponseException(String ex) {
        super(ex);
    }
}
