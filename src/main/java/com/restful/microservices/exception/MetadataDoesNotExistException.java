package com.restful.microservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MetadataDoesNotExistException extends RuntimeException{

    public MetadataDoesNotExistException(String msg){
        super(msg);
    }
}
