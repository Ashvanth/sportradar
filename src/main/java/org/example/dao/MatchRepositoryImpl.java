package org.example.dao;

import org.example.domain.Match;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MatchRepositoryImpl implements MatchRepository{

    private final Map<String, Match> matches;

    public MatchRepositoryImpl()
    {
        this.matches = new HashMap<>();
    }

    @Override
    public void save(Match match) throws SQLException {
        matches.put(match.getId(), match);
    }

    @Override
    public void delete(String homeTeam, String awayTeam) {
        Match matchObj = new Match(homeTeam,awayTeam);
       String key = findMatchId(matchObj);
      matches.remove(key);
    }

    @Override
    public void updateScore(Match match) {
        Optional<Match> matchObj = matches.values().stream()
                .filter(m -> m.getHomeTeam().equals(match.getHomeTeam()) && m.getAwayTeam().equals(match.getAwayTeam()))
                .findFirst();
       if(matchObj.isPresent())
       {
           matchObj.get().setHomeScore(match.getHomeScore());
           matchObj.get().setAwayScore(match.getAwayScore());
           matches.remove(matchObj.get().getId());
           matches.put(matchObj.get().getId(),matchObj.get());
       }

    }
    @Override
    public List<Match> findAll() throws SQLException{
        return matches.values().stream().toList();
    }

    public String findMatchId(Match match) {

        Optional<Match> matchObj = matches.values().stream()
                .filter(matchDTO2 -> matchDTO2.getHomeTeam().equalsIgnoreCase(match.getHomeTeam())
                        && matchDTO2.getAwayTeam().equalsIgnoreCase(match.getAwayTeam()))
                .findFirst();
        if(matchObj.isPresent()) {
            return matchObj.get().getId();
        }else {return null;}
    }
}
