package ticketsystem.service.repository.team;

import ticketsystem.service.model.data.TeamDTO;
import ticketsystem.service.repository.DataStore;

import java.util.List;

public class TeamSystemContext implements ITeamContext {

    static DataStore datastore = new DataStore();

    public List<TeamDTO> getTeams() {
        return datastore.getTeamList();
    }

    public TeamDTO getTeamByID(int ID) {
        return datastore.getTeamById(ID);
    }

    public TeamDTO getTeamByName(String name) {
        return datastore.getTeam(name);
    }

    public boolean createTeam(TeamDTO team) {
        return datastore.createTeam(team);
    }

    public boolean deleteTeam(int id) {
        return datastore.deleteTeam(id);
    }

    public boolean updateTeam(TeamDTO team) {
        return datastore.updateTeam(team.getID(), team);
    }
}
