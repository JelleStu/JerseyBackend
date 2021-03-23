package ticketsystem.service.logic;

import ticketsystem.service.model.data.TeamDTO;

import java.util.List;

public interface ITeamManager {
    List<TeamDTO> getTeams();
    TeamDTO getTeamById(int id);
    TeamDTO getTeamByName(String name);

    boolean createTeam(TeamDTO team) throws InterruptedException;
    boolean deleteTeam(int id);
    boolean updateTeam(TeamDTO team);
}
