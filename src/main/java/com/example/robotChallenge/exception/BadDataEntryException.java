package com.example.robotChallenge.exception;

public class BadDataEntryException extends Exception {
    public BadDataEntryException() {
        super("The data that you have entered have an issue");
    }
}
