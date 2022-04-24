package com.example.robotChallenge.worker;

import java.util.List;

import com.example.robotChallenge.exception.BadDataEntryException;
import com.example.robotChallenge.exception.EmptyFileException;
import com.example.robotChallenge.model.Plateau;
import com.example.robotChallenge.model.Rover;

public interface IRoversFollowingInstructionsWorker {
    public void dispatchData(String path) throws EmptyFileException, BadDataEntryException;
    public List<Rover> simulateInstructions(int xPlateau, int yPlateau, String[] roversToCreate,
    String[] roversInStructions) throws BadDataEntryException;
    public Rover followInstructions(Plateau plateau, Rover rover, String roversInStructionsStr)
            throws BadDataEntryException;
}
