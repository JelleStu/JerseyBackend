package ticketsystem.service.factory;

import ticketsystem.service.repository.match.IMatchContext;
import ticketsystem.service.repository.match.MatchHibernateContext;
import ticketsystem.service.repository.match.MatchSystemContext;

public class MatchContextFactory {
    public static IMatchContext CreateContext(String type){
        if (type.equals("Memory")){
            return new MatchSystemContext();
        }
        return new MatchHibernateContext();
    }
}
