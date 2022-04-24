package com.example.robotChallenge.factory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.robotChallenge.exception.BadDataEntryException;
import com.example.robotChallenge.model.Plateau;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class PlateauFactoryTest {
    PlateauFactory plateauFactory;

    @BeforeAll
    public void setup() {
        this.plateauFactory = new PlateauFactory();
    }

    @DisplayName("Create Plateau with positive coordinates")
    @Test
    public void createPlateauSuccessfulTest() throws BadDataEntryException {
        Plateau plateau = this.plateauFactory.createPlateau(5, 5);
        assertTrue(plateau.getX() == 5 && plateau.getY() == 5);
    }

    @DisplayName("Create Plateau with negative coordinates")
    @Test
    public void createPlateauFailingTest() throws BadDataEntryException {
        assertThrows(BadDataEntryException.class, () -> this.plateauFactory.createPlateau(-1, 2));
    }
}
