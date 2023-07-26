package org.example.dao;

import org.example.domain.Match;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface MatchRepository {
    void save(Match match) throws SQLException;
    void delete(String homeTeam, String awayTeam) throws SQLIntegrityConstraintViolationException;

    void updateScore(Match match) throws SQLDataException;
    List<Match> findAll() throws SQLException;
}
