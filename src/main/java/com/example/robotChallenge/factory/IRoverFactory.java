package com.example.robotChallenge.factory;

import com.example.robotChallenge.exception.BadDataEntryException;
import com.example.robotChallenge.model.Plateau;
import com.example.robotChallenge.model.Rover;

public interface IRoverFactory {
    public Rover createRover(int x, int y, String directionStr) throws BadDataEntryException;
    public Boolean validateMovement(Rover rover, Plateau plateau);
    public Rover advance(Rover rover);
    public Rover rotate(Rover rover, String rotationDirectionString) throws BadDataEntryException; 
}
