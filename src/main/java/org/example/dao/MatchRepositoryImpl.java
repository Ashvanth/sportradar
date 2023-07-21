package org.example.dao;

import org.example.domain.Match;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MatchRepositoryImpl implements MatchRepository{

    private final Map<String, Match> matches;

    public MatchRepositoryImpl()
    {
        this.matches = new HashMap<>();
    }

    @Override
    public void save(Match match) {
        matches.put(match.getHomeTeam() + " - " + match.getAwayTeam(), match);
    }

    @Override
    public void delete(String homeTeam, String awayTeam) {
      String key = matches.entrySet()
               .stream()
               .filter(val -> val.getValue().getAwayTeam().equalsIgnoreCase(awayTeam) && val.getValue().getHomeTeam().equalsIgnoreCase(homeTeam))
               .map(Map.Entry::getKey)
              .collect(Collectors.joining());
      matches.remove(key);
    }

    @Override
    public void updateScore(Match match) {
        matches.values().stream()
                .filter(m -> m.getHomeTeam().equals(match.getHomeTeam()) && m.getAwayTeam().equals(match.getAwayTeam()))
                .findFirst()
                .ifPresent(t -> t.updateScore(match.getHomeScore(), match.getAwayScore()));
    }
    @Override
    public List<Match> findAll() {
        return matches.values().stream().toList();
    }
}
