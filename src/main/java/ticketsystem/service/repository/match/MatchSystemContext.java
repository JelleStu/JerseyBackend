package ticketsystem.service.repository.match;

import ticketsystem.service.model.data.MatchDTO;
import ticketsystem.service.repository.DataStore;

import java.util.Collections;
import java.util.List;

public class MatchSystemContext implements IMatchContext {

    private static final DataStore dataStore = new DataStore();

    public List<MatchDTO> getMatches() {
        return dataStore.getMatchList();
    }

    public MatchDTO getMatchByID(int id) {
        return dataStore.getMatch(id);
    }

    public List<MatchDTO> getMatchesByHomeTeam(String name) {
        return dataStore.getMatchHomeTeam(name);
    }

    public List<MatchDTO> getMatchesByAwayTeam(String name) {
        return dataStore.getMatchesByAwayTeam(name);
    }

    public List<MatchDTO> getMatchesByTeam(int teamid) {
        return Collections.emptyList();
    }

    public List<MatchDTO> getMatchesByTeamId(int teamid) {
        return Collections.emptyList();
    }

    public boolean createMatch(MatchDTO match) {
        return dataStore.createMatch(match);
    }

    public void deleteMatch(MatchDTO match) {
        dataStore.deleteMatch(match);
    }

}
