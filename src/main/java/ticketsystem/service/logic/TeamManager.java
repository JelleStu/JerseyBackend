package ticketsystem.service.logic;

import ticketsystem.service.model.data.TeamDTO;
import ticketsystem.service.repository.team.ITeamContext;
import java.util.List;

public class TeamManager implements ITeamManager{

    ITeamContext context;

    public TeamManager(ITeamContext context){
        this.context = context;
    }
    public List<TeamDTO> getTeams() {
        return context.getTeams();
    }

    public TeamDTO getTeamById(int id) {
       return context.getTeamByID(id);
    }

    public TeamDTO getTeamByName(String name) {
       return context.getTeamByName(name);
    }

    public boolean createTeam(TeamDTO team) throws InterruptedException{
        return context.createTeam(team);
    }

    public boolean deleteTeam(int id) {
        return context.deleteTeam(id);
    }

    public boolean updateTeam(TeamDTO team) {
        return context.updateTeam(team);
    }
}
