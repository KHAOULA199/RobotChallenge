package com.example.robotChallenge.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.robotChallenge.exception.BadDataEntryException;
import com.example.robotChallenge.model.Direction;
import com.example.robotChallenge.model.Plateau;
import com.example.robotChallenge.model.Rover;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class RoverFactoryTest {
    IRoverFactory roverFactory;
    @BeforeEach
    public void before() {
        this.roverFactory = new RoverFactory();
    }
    @DisplayName("Create a rover with positive coordinates test")
    @Test
    public void createRoverSuccessfulTest() throws BadDataEntryException{
        Rover rover = this.roverFactory.createRover(1, 2, "N");
        assertTrue(rover.getX()==1 && rover.getY() ==2 && rover.getDirection().equals(Direction.NORTH));
    }
    @DisplayName("Create a rover with negative coordinates test")
    @Test
    public void createRoverFailingTest() throws BadDataEntryException{
        assertThrows(BadDataEntryException.class, ()->this.roverFactory.createRover(-1, 2, "N"));
    }
    @DisplayName("Create a rover with a bad direction test")
    @Test
    public void createRoverFailingTest2() throws BadDataEntryException{
        assertThrows(BadDataEntryException.class, ()->this.roverFactory.createRover(1, 2, "K"));
    }
    @DisplayName("validate a rover movement test")
    @Test
    public void validateMovementSuccessfulTest() throws BadDataEntryException{
        Rover rover = this.roverFactory.createRover(1, 2, "N");
        IPlateauFactory plateauFactory = new PlateauFactory();
        Plateau plateau = plateauFactory.createPlateau(5, 5);
        assertTrue(roverFactory.validateMovement(rover, plateau));

    }
    @DisplayName("validate a wrong Rover movement test")
    @Test
    public void validateMovementFailingTest() throws BadDataEntryException{
        Rover rover = this.roverFactory.createRover(1, 2, "N");
        IPlateauFactory plateauFactory = new PlateauFactory();
        Plateau plateau = plateauFactory.createPlateau(1, 1);
        assertFalse(roverFactory.validateMovement(rover, plateau));
    }
    @DisplayName("Advance a Rover test")
    @Test
    public void advanceRoverSuccessfulTest() throws BadDataEntryException{
        Rover rover = this.roverFactory.createRover(1, 2, "N");
        assertEquals(roverFactory.advance(rover).getY(),  3);

    }
    @DisplayName("Rotate a Rover test")
    @Test
    public void rotateRoverSuccessfulTest() throws BadDataEntryException{
        Rover rover = this.roverFactory.createRover(1, 2, "N");
        assertEquals(roverFactory.rotate(rover, "L").getDirection(),  Direction.WEST);

    }

}
