package com.edu.teamone.backendapp.exceptions;

public class UnauthrorizedRoleException extends Exception{
    public UnauthrorizedRoleException(String message){
        super(message);
    }
}
