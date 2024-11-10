package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

import pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.controller.InvalidName;

public class Player {
    private String name;
    private int score;

    public Player(String name) throws InvalidName {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }
}
