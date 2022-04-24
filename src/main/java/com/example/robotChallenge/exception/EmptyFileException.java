package com.example.robotChallenge.exception;


public class EmptyFileException extends Exception {
    public EmptyFileException() {
        super("The data that you have entered have an issue");
    }
}