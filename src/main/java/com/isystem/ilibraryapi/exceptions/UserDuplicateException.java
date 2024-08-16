package com.isystem.ilibraryapi.exceptions;

public class UserDuplicateException extends RuntimeException{

    public UserDuplicateException(String msg) {
        super(msg);
    }
}
