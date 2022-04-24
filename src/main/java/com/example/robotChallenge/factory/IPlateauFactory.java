package com.example.robotChallenge.factory;

import com.example.robotChallenge.exception.BadDataEntryException;
import com.example.robotChallenge.model.Plateau;

public interface IPlateauFactory {
    public Plateau createPlateau(int x, int y) throws BadDataEntryException;
}
