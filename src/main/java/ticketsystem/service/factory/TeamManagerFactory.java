package ticketsystem.service.factory;

import ticketsystem.service.CustomApplicationConfig;
import ticketsystem.service.logic.ITeamManager;
import ticketsystem.service.logic.TeamManager;
import ticketsystem.service.repository.team.ITeamContext;

public class TeamManagerFactory {
    public static ITeamManager CreateTeamManager(){
        ITeamContext context = TeamContextFactory.createTeamContext(CustomApplicationConfig.DATA_ENVIRONMENT);
        return new TeamManager(context);
    }
    public static ITeamManager CreateTeamManager(String type){
        ITeamContext context = TeamContextFactory.createTeamContext(type);
        return new TeamManager(context);
    }
}
