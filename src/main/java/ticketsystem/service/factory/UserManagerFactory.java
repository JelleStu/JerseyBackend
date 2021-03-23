package ticketsystem.service.factory;

import ticketsystem.service.logic.IUserManager;
import ticketsystem.service.logic.UserManager;

public class UserManagerFactory {

    public static IUserManager CreateUserManager(String contextType){
        return new UserManager(UserContextFactory.createContext(contextType));
    }
}
