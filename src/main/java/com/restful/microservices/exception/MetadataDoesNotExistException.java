package com.restful.microservices.exception;

public class MetadataDoesNotExistException extends RuntimeException{

    public MetadataDoesNotExistException(String msg){
        super(msg);
    }
}
