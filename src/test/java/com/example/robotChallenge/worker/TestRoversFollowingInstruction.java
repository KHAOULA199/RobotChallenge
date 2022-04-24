package com.example.robotChallenge.worker;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.example.robotChallenge.exception.BadDataEntryException;
import com.example.robotChallenge.factory.PlateauFactory;
import com.example.robotChallenge.factory.RoverFactory;
import com.example.robotChallenge.model.Direction;
import com.example.robotChallenge.model.Plateau;
import com.example.robotChallenge.model.Rover;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class TestRoversFollowingInstruction {
    RoversFollowingInstructionsWorker roversFollowingInstructions;

    @BeforeAll
    public void before() {
        this.roversFollowingInstructions = RoversFollowingInstructionsWorker.getInstance();
    }

    @DisplayName("Make a Rover follow the instructions test")
    @Test
    public void FollowInstructionSuccessfulTest() throws BadDataEntryException {
        Rover rover = new RoverFactory().createRover(1, 2, "N");
        Plateau plateau = new PlateauFactory().createPlateau(5, 5);
        rover = this.roversFollowingInstructions.followInstructions(plateau, rover, "LMLMLMLMM");
        assertTrue(rover.getX() == 1 && rover.getY() == 3 && rover.getDirection().equals(Direction.NORTH));
    }

    @DisplayName("simulate the rovers following instruction test test")
    @Test
    public void simulateInstructionsSuccessfulTest() throws BadDataEntryException {
        List<Rover> rovers = this.roversFollowingInstructions.simulateInstructions(5, 5, new String[] { "1 2 N" },
                new String[] { "LMLMLMLMM" });
        assertTrue(
            rovers.size() == 1 &&
            rovers.get(0).getX() == 1 && rovers.get(0).getY() == 3
                && rovers.get(0).getDirection().equals(Direction.NORTH));
    }
}
