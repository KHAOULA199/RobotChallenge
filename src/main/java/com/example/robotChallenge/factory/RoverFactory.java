package com.example.robotChallenge.factory;

import com.example.robotChallenge.exception.BadDataEntryException;
import com.example.robotChallenge.model.Direction;
import com.example.robotChallenge.model.Plateau;
import com.example.robotChallenge.model.Rover;

public class RoverFactory implements IRoverFactory{
    
    /** 
     * @param x
     * @param y
     * @param directionStr
     * @return Rover
     * @throws BadDataEntryException
     */
    public Rover createRover(int x, int y, String directionStr) throws BadDataEntryException {
        Direction direction = Direction.fromValue(directionStr.trim());
        if (x < 0 || y < 0 || direction.equals(null))
            throw new BadDataEntryException();
        return new Rover(x, y, direction);
    }

    
    /** 
     * @param rover
     * @param plateau
     * @return Boolean
     */
    public Boolean validateMovement(Rover rover, Plateau plateau) {
        if ((rover.getDirection().equals(Direction.NORTH) && rover.getY() + 1 > plateau.getY())
                || (rover.getDirection().equals(Direction.EAST) && rover.getX() + 1 > plateau.getX())
                || (rover.getDirection().equals(Direction.SOUTH) && rover.getY() - 1 < 0)
                || (rover.getDirection().equals(Direction.WEST) && rover.getX() + 1 < 0))
            return false;
        return true;
    }
    
    /** 
     * @param rover
     * @return Rover
     */
    public Rover advance(Rover rover){
        switch (rover.getDirection()) {
            case NORTH:
                rover.setY(rover.getY() + 1);
                break;
            case EAST:
                rover.setX(rover.getX() + 1);
                break;
            case SOUTH:
                rover.setY(rover.getY() - 1);
                break;
            case WEST:
                rover.setX(rover.getX() - 1);
                break;
        }
        return rover;
    }
    
    /** 
     * @param rover
     * @param rotationDirectionString
     * @return Rover
     * @throws BadDataEntryException
     */
    public Rover rotate(Rover rover, String rotationDirectionString) throws BadDataEntryException{
        switch(rotationDirectionString){
            case "L":
                 rover.setDirection(Direction.rotateToLeft(rover.getDirection()));
                 return rover;
            case "R":
                 rover.setDirection(Direction.rotateToRight(rover.getDirection()));
                 return rover;
        }
            throw new BadDataEntryException();

    }
}
