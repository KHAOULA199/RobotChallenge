package com.example.robotChallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rover {
    private int x;
    private int y;
    private Direction direction;

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return this.x + " " + this.y + " " + this.direction.toString();
    }
    
}
