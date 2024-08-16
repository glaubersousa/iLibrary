package com.isystem.ilibraryapi.exceptions;

public class BookDuplicateException extends RuntimeException{

    public BookDuplicateException(String msg) {
        super(msg);
    }
}
