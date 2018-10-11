package fr.wcs.galaxian_atelier_firebase;

public class Player {

    private String name;
    private int bestScore;

    public Player(String name, int bestScore) {
        this.name = name;
        this.bestScore = bestScore;
    }

    public Player() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }
}
