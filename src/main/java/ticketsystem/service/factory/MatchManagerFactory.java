package ticketsystem.service.factory;

import ticketsystem.service.logic.MatchManager;
import ticketsystem.service.repository.match.IMatchContext;

public class MatchManagerFactory {
    public static MatchManager createMatchManager(String type){
        IMatchContext context = MatchContextFactory.CreateContext(type);
        return new MatchManager(context);
    }
}
