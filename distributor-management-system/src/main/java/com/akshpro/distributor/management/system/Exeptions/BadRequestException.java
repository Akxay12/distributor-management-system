package com.akshpro.distributor.management.system.Exeptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message){
        super(message);
    }
}