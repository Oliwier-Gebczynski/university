package pl.polsl.lab1.oliwier.gebczynski.myfirstmvp.model;

/**
 *
 * @author Oliwier Gebczynski
 */
public class Player {

    String name;
    int score = 0;
    int record = 0;

    public Player(String playerName) {
        name = playerName;
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getRecord() {
        return record;
    }

    //Setters
    public void setName(String newName) {
        this.name = newName;
    }

    public void setScore(int newScore) {
        this.score = newScore;
    }

    public void setRecord(int newRecord) {
        this.record = newRecord;
    }
}
