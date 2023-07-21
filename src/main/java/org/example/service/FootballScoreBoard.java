package org.example.service;


import org.example.dao.MatchRepositoryImpl;
import org.example.domain.Match;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FootballScoreBoard {

    private List<Match> matches;
    private MatchRepositoryImpl matchRepository;


    public FootballScoreBoard() {
        this.matches = new ArrayList<>();
        this.matchRepository = new MatchRepositoryImpl();
    }

    public void startGame(String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        matchRepository.save(match);
    }

    public void finishGame(String homeTeam, String awayTeam) {
       matchRepository.delete(homeTeam,awayTeam);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Match match = new Match(homeTeam,awayTeam);
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
        matchRepository.updateScore(match);
    }


    public List<Match> getSummaryByTotalScore() {
       List<Match> matchList = matchRepository.findAll();
       return matchList.stream()
                .sorted(Comparator.comparingInt(Match::getTotalScore).reversed().thenComparing(matches::indexOf))
                .collect(Collectors.toList());
    }

}
