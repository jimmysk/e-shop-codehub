package com.codehub.spring.eshop.exception;

/**
 * Created by Dimitris on 21/5/2018.
 */
public class EmailExistsException extends Exception {

    public EmailExistsException(String msg) {
        super(msg);
    }
}
