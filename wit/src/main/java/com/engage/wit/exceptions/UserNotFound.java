package com.engage.wit.exceptions;

public class UserNotFound extends Exception{
    public UserNotFound(String s)
    {
        // Call constructor of parent Exception
        super(s);
    }
}