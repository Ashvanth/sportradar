package org.example;

import org.example.domain.Match;
import org.example.service.FootballScoreBoard;

import java.util.List;

public class LiveFootBallScore {

    public static void main(String[] args) {
        FootballScoreBoard scoreBoard = new FootballScoreBoard();

        scoreBoard.startGame("Mexico", "Canada");
        scoreBoard.startGame("Spain", "Brazil");
        scoreBoard.startGame("Germany", "France");
        scoreBoard.startGame("Uruguay", "Italy");
        scoreBoard.startGame("Argentina", "Australia");

        scoreBoard.updateScore("Mexico", "Canada", 0, 5);
        scoreBoard.updateScore("Spain", "Brazil", 10, 2);
        scoreBoard.updateScore("Germany", "France", 2, 2);
        scoreBoard.updateScore("Uruguay", "Italy", 6, 6);
        scoreBoard.updateScore("Argentina", "Australia", 3, 1);

        List<Match> summary = scoreBoard.getSummaryByTotalScore();

        int rank = 1;
        for (Match match : summary) {
            System.out.println(rank + ". " + match.getHomeTeam() + " " + match.getHomeScore()
                    + " - " + match.getAwayTeam() + " " + match.getAwayScore());
            rank++;
        }
    }
}
