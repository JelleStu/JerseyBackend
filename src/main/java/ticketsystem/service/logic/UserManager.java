package ticketsystem.service.logic;

import ticketsystem.service.model.data.UserDTO;
import ticketsystem.service.repository.user.IUserContext;

public class UserManager implements IUserManager {

    IUserContext context;

    public UserManager(IUserContext context){
        this.context = context;
    }

    public UserDTO login(UserDTO user) {
       return context.login(user);
    }

    public boolean register(UserDTO user){
        return context.register(user);
    }

    public int getRoleFromUser(UserDTO user) {
        return context.getRoleFromUser(user);
    }
}
