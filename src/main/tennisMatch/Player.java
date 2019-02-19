package tennisMatch;

import java.util.HashMap;

public class Player {

    private String name;
    private String score;
    private int sets;
    private int games;
    private HashMap<Integer, Integer> gamesInSets = new HashMap<>();

    public Player(String name) {
        this.name = name;
        this.score = "0";
        this.sets = 0;
        this.games = 0;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getSets() {
        return sets;
    }

    public int getGames() {
        return games;
    }

    public void winGame() {
        games ++;
        score = "0";
    }

    public void loseGame() {
        score = "0";
    }

    public void winSet() {
        sets ++;
        gamesInSets.put(gamesInSets.size() + 1, games);
        games = 0;
    }

    public void loseSet() {
        gamesInSets.put(gamesInSets.size() + 1, games);
        games = 0;
    }

    public int getGamesInSets(int setNumber) {
        return gamesInSets.get(setNumber);
    }

    public String toString() {
        return "Joueur " + getName() + ": S " + getScore()
                + ", G " + getGames()
                + ", S " + getSets();
    }
}
