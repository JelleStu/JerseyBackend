package ticketsystem.service.factory;

import ticketsystem.service.repository.team.ITeamContext;
import ticketsystem.service.repository.team.TeamHibernateContext;
import ticketsystem.service.repository.team.TeamSystemContext;

public class TeamContextFactory {
    public static ITeamContext createTeamContext(String type){
        if (type.equals("Memory")){
            return new TeamSystemContext();
        }
            return new TeamHibernateContext();
    }
}
