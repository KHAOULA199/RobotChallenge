package com.example.robotChallenge.model;

import com.example.robotChallenge.exception.BadDataEntryException;

public enum Direction {
    EAST, WEST, SOUTH, NORTH;

    public static Direction fromValue(String directionName) throws BadDataEntryException {
        switch (directionName) {
            case "N":
                return Direction.NORTH;
            case "S":
                return Direction.SOUTH;
            case "E":
                return Direction.EAST;
            case "W":
                return Direction.WEST;

        }
        throw new BadDataEntryException();

    }

    public static Direction rotateToLeft(Direction initialDirection) {
        switch (initialDirection) {
            case NORTH:
                return Direction.WEST;
            case SOUTH:
                return Direction.EAST;
            case EAST:
                return Direction.NORTH;
            case WEST:
                return Direction.SOUTH;
        }
        return null;
    }

    public static Direction rotateToRight(Direction initialDirection) {
        switch (initialDirection) {
            case NORTH:
                return Direction.EAST;
            case SOUTH:
                return Direction.WEST;
            case EAST:
                return Direction.SOUTH;
            case WEST:
                return Direction.NORTH;
        }
        return null;
    }
    @Override
    public String toString(){
        switch (this) {
            case NORTH:
                return "N";
            case SOUTH:
                return "S";
            case EAST:
                return "E";
            case WEST:
                return "W";
        }
        return "";
    }

}
