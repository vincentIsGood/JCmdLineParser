package com.vincentcodes.util.commandline.exceptions;

public class InvalidType extends RuntimeException{
    public InvalidType(){}

    public InvalidType(String message){
        super(message);
    }
}
