package com.vincentcodes.util.commandline.exceptions;

public class ConversionException extends RuntimeException{
    public ConversionException(){}
    
    public ConversionException(String message){
        super(message);
    }
}
