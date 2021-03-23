package ticketsystem.service.logic;

import ticketsystem.service.model.view.MatchView;

import java.util.List;

public interface IMatchManager{
    List<MatchView> getMatches();
    MatchView getMatchById(int id);
    List<MatchView> getMatchesByHomeTeam(String name);
    List<MatchView> getMatchesByAwayTeam(String name);
    List<MatchView> getMatchesByTeam(String name);
    List<MatchView> getMatchesByTeamId(int teamid);
    boolean createMatch(MatchView match);
    void deleteMatch(MatchView match);
}
