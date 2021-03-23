package ticketsystem.service.repository.team;

import ticketsystem.service.model.data.TeamDTO;

import java.util.List;

public interface ITeamContext {
    List<TeamDTO> getTeams();
    TeamDTO getTeamByID(int ID);
    TeamDTO getTeamByName(String name);
    boolean createTeam(TeamDTO team);
    boolean deleteTeam(int id);
    boolean updateTeam(TeamDTO team);
}
