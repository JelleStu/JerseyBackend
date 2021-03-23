package ticketsystem.service.factory;

import ticketsystem.service.repository.user.UserMemoryContext;
import ticketsystem.service.repository.user.IUserContext;
import ticketsystem.service.repository.user.UserHiberateContext;

public class UserContextFactory {
    public static IUserContext createContext(String type){
        if (type.equals("Memory")){
            return new UserMemoryContext();
        }
        return new UserHiberateContext();
    }
}
