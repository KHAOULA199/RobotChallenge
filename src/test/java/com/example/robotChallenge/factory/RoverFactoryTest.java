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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class RoverFactoryTest {
    RoverFactory roverFactory;
    @BeforeEach
    public void before() {
        this.roverFactory = new RoverFactory();
    }
    @DisplayName("Create Rover with positive coordinates")
    @Test
    public void createRoverSuccessfulTest() throws BadDataEntryException{
        Rover rover = this.roverFactory.createRover(1, 2, "N");
        assertTrue(rover.getX()==1 && rover.getY() ==2 && rover.getDirection().equals(Direction.NORTH));
    }
    @DisplayName("Create Rover with negative coordinates")
    @Test
    public void createRoverFailingTest() throws BadDataEntryException{
        assertThrows(BadDataEntryException.class, ()->this.roverFactory.createRover(-1, 2, "N"));
    }
    @DisplayName("Create Rover with a bad direction")
    @Test
    public void createRoverFailingTest2() throws BadDataEntryException{
        assertThrows(BadDataEntryException.class, ()->this.roverFactory.createRover(1, 2, "K"));
    }
    @DisplayName("validate a Rover movement")
    @Test
    public void validateMovementSuccessfulTest() throws BadDataEntryException{
        Rover rover = this.roverFactory.createRover(1, 2, "N");
        PlateauFactory plateauFactory = new PlateauFactory();
        Plateau plateau = plateauFactory.createPlateau(5, 5);
        assertTrue(roverFactory.validateMovement(rover, plateau));

    }
    @DisplayName("validate a wrong Rover movement")
    @Test
    @Disabled
    public void validateMovementFailingTest() throws BadDataEntryException{
        Rover rover = this.roverFactory.createRover(1, 2, "N");
        PlateauFactory plateauFactory = new PlateauFactory();
        Plateau plateau = plateauFactory.createPlateau(1, 1);
        assertFalse(roverFactory.validateMovement(rover, plateau));
    }
    @DisplayName("advance a Rover")
    @Test
    public void advanceRoverSuccessfulTest() throws BadDataEntryException{
        Rover rover = this.roverFactory.createRover(1, 2, "N");
        assertEquals(roverFactory.advance(rover).getY(),  3);

    }
    @DisplayName("rotate a Rover")
    @Test
    public void rotateRoverSuccessfulTest() throws BadDataEntryException{
        Rover rover = this.roverFactory.createRover(1, 2, "N");
        assertEquals(roverFactory.rotate(rover, "L").getDirection(),  Direction.WEST);

    }

}
