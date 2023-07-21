package org.example.dao;

import org.example.domain.Match;

import java.util.List;

public interface MatchRepository {
    void save(Match match);
    void delete(String homeTeam, String awayTeam);

    void updateScore(Match match);
    List<Match> findAll();
}
