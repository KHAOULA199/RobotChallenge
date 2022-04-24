package com.example.robotChallenge.model;

import lombok.Data;

@Data
public final class Plateau {
    final private int x;
    final private int y;
    private static Plateau plateau;

    private Plateau(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public static Plateau getPlateau(int x, int y){
        if (plateau == null)
            plateau = new Plateau( x, y);
        return plateau;
    }

}
