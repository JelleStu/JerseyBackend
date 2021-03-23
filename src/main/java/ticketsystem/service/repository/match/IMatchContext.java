package ticketsystem.service.repository.match;

import ticketsystem.service.model.data.MatchDTO;

import java.util.List;

public interface IMatchContext {
    List<MatchDTO> getMatches();
    MatchDTO getMatchByID(int id);
    List<MatchDTO> getMatchesByHomeTeam(String name);
    List<MatchDTO> getMatchesByAwayTeam(String name);
    List<MatchDTO> getMatchesByTeam(int teamid);
    List<MatchDTO> getMatchesByTeamId(int teamid);
    boolean createMatch(MatchDTO match);
    void deleteMatch(MatchDTO match);
}
