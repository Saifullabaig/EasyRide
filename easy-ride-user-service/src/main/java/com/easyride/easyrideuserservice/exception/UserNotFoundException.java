package com.easyride.easyrideuserservice.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {

    String message;

    public UserNotFoundException(String message) {
        super();
        this.message = message;
    }

    public UserNotFoundException() {
        super();
    }

}

