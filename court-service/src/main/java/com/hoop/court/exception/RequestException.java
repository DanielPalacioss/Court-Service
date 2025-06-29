package com.hoop.court.exception;

import lombok.Getter;

@Getter
public class RequestException extends RuntimeException{
    private String code;
     public RequestException(String message, String code){
         super(message);
         this.code=code;
     }
}
