package org.example.service;


import org.example.domain.Match;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FootballScoreBoard {

    private List<Match> matches;

    public FootballScoreBoard() {
        this.matches = new ArrayList<>();
    }

    public void startGame(String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        matches.add(match);
    }

    public void finishGame(String homeTeam, String awayTeam) {
        matches.removeIf(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam));
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        matches.stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .findFirst()
                .ifPresent(match -> match.updateScore(homeScore, awayScore));
    }


    public List<Match> getSummaryByTotalScore() {
        return matches.stream()
                .sorted(Comparator.comparingInt(Match::getTotalScore).reversed().thenComparing(matches::indexOf))
                .collect(Collectors.toList());
    }

}
