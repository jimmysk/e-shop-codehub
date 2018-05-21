package com.codehub.spring.eshop.exception;

/**
 * Created by Dimitris on 21/5/2018.
 */
public class UserNotAuthException extends Exception {

    public UserNotAuthException(String msg) {
        super(msg);
    }
}
