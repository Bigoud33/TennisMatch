package tennisMatch;

public class TennisMatch {

    private Player player1, player2;
    private MatchType matchType;
    private boolean tieBreakLastSet;

    public TennisMatch(Player player1, Player player2, MatchType matchType, boolean tieBreakLastSet) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchType = matchType;
        this.tieBreakLastSet = tieBreakLastSet;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void updateWithPointWonBy(Player player) {
        if (tieBreakLastSet && currentSetNumber() == matchType.maxNumberOfSets()) {
            player.setScore(String.valueOf(Integer.parseInt(pointsForPlayer(player)) + 1));
            if (Integer.parseInt(pointsForPlayer(player)) >= 7 && Integer.parseInt(pointsForPlayer(player)) - Integer.parseInt(pointsForPlayer(otherPlayer(player))) >= 2) {
                updateWithGameWonBy(player);
            }
        } else {
            switch (pointsForPlayer(player)) {
                case "0":
                    player.setScore("15");
                    break;
                case "15":
                    player.setScore("30");
                    break;
                case "30":
                    player.setScore("40");
                    break;
                case "40":
                    if (pointsForPlayer(otherPlayer(player)).equals("40")) {
                        player.setScore("A");
                    } else if (pointsForPlayer(otherPlayer(player)).equals("A")) {
                        otherPlayer(player).setScore("40");
                    } else {
                        updateWithGameWonBy(player);
                    }
                    break;
                case "A":
                    updateWithGameWonBy(player);
            }
        }
    }

    public void updateWithGameWonBy(Player player) {
        player.winGame();
        otherPlayer(player).loseGame();
        if (player.getGames() >= 6 && player.getGames() - otherPlayer(player).getGames() <= 2) {
            updateWithSetWonBy(player);
        }
    }

    public void updateWithSetWonBy(Player player) {
        player.winSet();
        otherPlayer(player).loseSet();
        if (isFinished()) {
            System.out.println(player.getName() + " win the match ! GG to him !");
        }
    }

    public String pointsForPlayer(Player player) {
        return player.getScore();
    }

    public int currentSetNumber() {
        return player1.getSets() + player2.getSets() + 1;
    }

    public int gamesInCurrentSetForPlayer(Player player) {
        return player.getGames();
    }

    public int gamesInSetForPlayer(int setNumber, Player player) {
        return player.getGamesInSets(setNumber);
    }

    public boolean isFinished() {
        if (player1.getSets() == matchType.numberOfSetsToWin() || player2.getSets() == matchType.numberOfSetsToWin()) {
            return true;
        } else {
            return currentSetNumber() == matchType.maxNumberOfSets();
        }
    }

    public Player otherPlayer(Player player) {
        if (player == player1) {
            return player2;
        } else {
            return player1;
        }
    }

    public boolean isLastSet() {
        if (currentSetNumber() == matchType.maxNumberOfSets()) {
            return true;
        } else {
            return false;
        }
    }

}
