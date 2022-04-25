package com.example.robotChallenge.factory;

import com.example.robotChallenge.exception.BadDataEntryException;
import com.example.robotChallenge.model.Plateau;

public class PlateauFactory implements IPlateauFactory{
    
    /** 
     * Creates a plateau using x and y
     * @param x
     * @param y
     * @return Plateau
     * @throws BadDataEntryException
     */
    public Plateau createPlateau(int x, int y) throws BadDataEntryException {
        if (x < 0 || y < 0)
            throw new BadDataEntryException();
        return Plateau.getPlateau(x, y);
    }
}
