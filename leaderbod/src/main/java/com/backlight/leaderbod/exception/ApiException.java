package com.backlight.leaderbod.exception;


//class for exception
public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
    public ApiException(){
        super();
    }

}

