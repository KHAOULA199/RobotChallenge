package com.example.robotChallenge.exception;

import java.io.IOException;

public class BadDataEntryException extends IOException {
    public BadDataEntryException() {
        super("The data that you have entered have an issue");
    }
}
