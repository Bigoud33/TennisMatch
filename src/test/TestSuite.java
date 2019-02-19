import org.junit.Test;
import tennisMatch.MatchType;
import tennisMatch.Player;
import tennisMatch.TennisMatch;

import static org.junit.Assert.assertEquals;

public class TestSuite {
    Player player1;
    Player player2;
    TennisMatch m;

    @Test
    public void winJeuSansAvantage() {
        player1 = new Player("Roger Federer");
        player2 = new Player("Rafael Nadal");
        m = new TennisMatch(player1, player2, MatchType.BEST_OF_THREE, false);

        //Début match
        m.updateWithPointWonBy(m.getPlayer1()); //J1 15
        m.updateWithPointWonBy(m.getPlayer1()); //J1 30
        m.updateWithPointWonBy(m.getPlayer2()); //J2 15
        m.updateWithPointWonBy(m.getPlayer1()); //J1 40
        m.updateWithPointWonBy(m.getPlayer2()); //J2 30
        m.updateWithPointWonBy(m.getPlayer1()); //J1 gagne le jeu

        assertEquals(1, m.getPlayer1().getGames()); // On vérifie si J1 a bien gagné 1 jeu
    }

    @Test
    public void winJeuAvecAvantage() {
        player1 = new Player("Roger Federer");
        player2 = new Player("Rafael Nadal");
        m = new TennisMatch(player1, player2, MatchType.BEST_OF_THREE, false);

        //Début du match
        m.updateWithPointWonBy(m.getPlayer1()); //J1 15
        m.updateWithPointWonBy(m.getPlayer1()); //J1 30
        m.updateWithPointWonBy(m.getPlayer2()); //J2 15
        m.updateWithPointWonBy(m.getPlayer1()); //J1 40
        m.updateWithPointWonBy(m.getPlayer2()); //J2 30
        m.updateWithPointWonBy(m.getPlayer2()); //J2 40
        m.updateWithPointWonBy(m.getPlayer1()); //J1 avantage, J2 40
        m.updateWithPointWonBy(m.getPlayer2()); //J2 reste à 40, J1 repasse 40

        assertEquals("40", m.pointsForPlayer(m.getPlayer2())); //On vérifie que J2 est à 40

        m.updateWithPointWonBy(m.getPlayer1()); //J1 passe à avantage, J2 reste 40

        assertEquals("A", m.pointsForPlayer(m.getPlayer1())); //On vérifie que J1 est à Avantage

        m.updateWithPointWonBy(m.getPlayer1()); //J1 gagne

        assertEquals(1, m.getPlayer1().getGames()); //On vérifie que J1 a bien gagné 1 jeu
    }

    @Test
    public void winSetNormal() {
        player1 = new Player("Roger Federer");
        player2 = new Player("Rafael Nadal");
        m = new TennisMatch(player1, player2, MatchType.BEST_OF_THREE, false);

        for (int i = 0; i < 4; i++) {
            m.getPlayer1().winGame();
            m.getPlayer2().winGame();
        }

        //Début du match, les joueurs commencent avec chacun 4 jeux
        m.updateWithPointWonBy(m.getPlayer1()); //J1 15
        m.updateWithPointWonBy(m.getPlayer1()); //J1 30
        m.updateWithPointWonBy(m.getPlayer1()); //J1 40
        m.updateWithPointWonBy(m.getPlayer1()); //J1 gagne un jeu
        System.out.println("P1 score: "+m.pointsForPlayer(m.getPlayer1())+", P1 games: "+ m.getPlayer1().getGames());
        m.updateWithPointWonBy(m.getPlayer1()); //J1 15
        m.updateWithPointWonBy(m.getPlayer1()); //J1 30
        m.updateWithPointWonBy(m.getPlayer1()); //J1 40
        m.updateWithPointWonBy(m.getPlayer1()); //J1 gagne un jeu
        System.out.println("P1 score: "+m.pointsForPlayer(m.getPlayer1())+", P1 games: "+ m.getPlayer1().getGames());
        System.out.println("P1 set: "+m.getPlayer1().getSets());
        //J1 est à 6 jeux et au moins 2 de plus que J2 donc J1 gagne le set

        assertEquals(1, m.getPlayer1().getSets()); //Le test passe si player 1 a bien gagné 1 jeu
    }

    @Test
    public void winSetNormalEquals5() {
        player1 = new Player("Roger Federer");
        player2 = new Player("Rafael Nadal");
        m = new TennisMatch(player1, player2, MatchType.BEST_OF_THREE, false);

        for (int i = 0; i < 4; i++) {
            m.getPlayer1().winGame();
            m.getPlayer2().winGame();
        }

        //Début du match, les joueurs commencent avec chacun 4 jeux
        m.updateWithPointWonBy(m.getPlayer1()); //J1 15
        m.updateWithPointWonBy(m.getPlayer1()); //J1 30
        m.updateWithPointWonBy(m.getPlayer1()); //J1 40
        m.updateWithPointWonBy(m.getPlayer1()); //J1 gagne un jeu (passage à 5 jeux)
        System.out.println("P1 score: "+m.pointsForPlayer(m.getPlayer1())+", P1 games: "+ m.getPlayer1().getGames());
        m.updateWithPointWonBy(m.getPlayer2()); //J2 15
        m.updateWithPointWonBy(m.getPlayer2()); //J2 30
        m.updateWithPointWonBy(m.getPlayer2()); //J2 40
        m.updateWithPointWonBy(m.getPlayer2()); //J2 gagne un jeu (passage à 5 jeux)
        System.out.println("P2 score: "+m.pointsForPlayer(m.getPlayer2())+", P2 games: "+ m.getPlayer2().getGames());
        System.out.println("Egalité à 5 ! On continue jusqu'à 7-5");
        m.updateWithPointWonBy(m.getPlayer1()); //J1 15
        m.updateWithPointWonBy(m.getPlayer1()); //J1 30
        m.updateWithPointWonBy(m.getPlayer1()); //J1 40
        m.updateWithPointWonBy(m.getPlayer1()); //J1 gagne un jeu (passage à 6)
        System.out.println("P1 score: "+m.pointsForPlayer(m.getPlayer1())+", P1 games: "+ m.getPlayer1().getGames());
        m.updateWithPointWonBy(m.getPlayer1()); //J1 15
        m.updateWithPointWonBy(m.getPlayer1()); //J1 30
        m.updateWithPointWonBy(m.getPlayer1()); //J1 40
        m.updateWithPointWonBy(m.getPlayer1()); //J1 gagne un jeu (passage à 7)
        System.out.println("P1 score: "+m.pointsForPlayer(m.getPlayer1())+", P1 games: "+ m.getPlayer1().getGames());

        System.out.println("P1 set: "+m.getPlayer1().getSets());

        assertEquals(1, m.getPlayer1().getSets()); //Le test passe si player 1 a bien gagné 1 jeu
    }

    @Test
    public void winSetAvecTieBreak() {
        player1 = new Player("Roger Federer");
        player2 = new Player("Rafael Nadal");
        m = new TennisMatch(player1, player2, MatchType.BEST_OF_THREE, true);

        m.getPlayer1().winSet();
        m.getPlayer2().winSet();

        for (int i = 0; i < 5; i++) {
            m.getPlayer1().winGame();
            m.getPlayer2().winGame();
        }

        //Début du match, les joueurs commencent avec chacun 5 jeux
        m.updateWithPointWonBy(m.getPlayer1()); //J1 1
        m.updateWithPointWonBy(m.getPlayer1()); //J1 2
        m.updateWithPointWonBy(m.getPlayer1()); //J1 3
        m.updateWithPointWonBy(m.getPlayer1()); //J1 4
        m.updateWithPointWonBy(m.getPlayer1()); //J1 5
        m.updateWithPointWonBy(m.getPlayer1()); //J1 6
        m.updateWithPointWonBy(m.getPlayer2()); //J2 1
        m.updateWithPointWonBy(m.getPlayer2()); //J2 2
        m.updateWithPointWonBy(m.getPlayer2()); //J2 3
        m.updateWithPointWonBy(m.getPlayer2()); //J2 4
        m.updateWithPointWonBy(m.getPlayer2()); //J2 5
        m.updateWithPointWonBy(m.getPlayer2()); //J2 6
        m.updateWithPointWonBy(m.getPlayer1()); //J1 7
        m.updateWithPointWonBy(m.getPlayer2()); //J2 7


        System.out.println("-------------------------");
        System.out.println("P1 games: "+m.getPlayer1().getGames());
        System.out.println("P2 games: "+m.getPlayer2().getGames());
        System.out.println("P1 set: "+m.getPlayer1().getSets());
        System.out.println("P2 set: "+m.getPlayer2().getSets());

        assertEquals(1, m.getPlayer1().getSets()); //Le test passe si player 1 a bien gagné 1 jeu

        m.updateWithPointWonBy(m.getPlayer2()); //J2 8
        m.updateWithPointWonBy(m.getPlayer2()); //J2 9

        assertEquals(2, m.getPlayer2().getSets()); //Le test passe si player 2 a bien gagné 2 jeux

    }

    @Test //Dernier set: pas de tie break possible, jeux qui peuvent aller plus haut que 6
    public void winSetSansTieBreak() {
        player1 = new Player("Roger Federer");
        player2 = new Player("Rafael Nadal");
        m = new TennisMatch(player1, player2, MatchType.BEST_OF_THREE, false);

        m.getPlayer1().winSet();
        m.getPlayer2().winSet();

        for (int i = 0; i < 5; i++) {
            m.getPlayer1().winGame();
            m.getPlayer2().winGame();
        }

        //Début du match, les joueurs commencent avec chacun 5 jeux
        m.updateWithPointWonBy(m.getPlayer1()); //J1 15
        m.updateWithPointWonBy(m.getPlayer1()); //J1 30
        m.updateWithPointWonBy(m.getPlayer1()); //J1 40
        System.out.println("P1 set: "+m.getPlayer1().getSets());
        m.updateWithPointWonBy(m.getPlayer2()); //J2 15
        m.updateWithPointWonBy(m.getPlayer2()); //J2 30
        m.updateWithPointWonBy(m.getPlayer2()); //J2 40
        m.updateWithPointWonBy(m.getPlayer2()); //J2 A
        m.updateWithPointWonBy(m.getPlayer2()); //J2 gagne le jeu et le set
        System.out.println("P2 set: "+m.getPlayer2().getSets());
        System.out.println("-------------------------");
        System.out.println("P1 games: "+m.getPlayer1().getGames());
        System.out.println("P2 games: "+m.getPlayer2().getGames());
        System.out.println("P1 set: "+m.getPlayer1().getSets());
        System.out.println("P2 set: "+m.getPlayer2().getSets());

        assertEquals(2, m.getPlayer2().getSets()); //Le test passe si player 1 a bien gagné 1 jeu
    }

}
