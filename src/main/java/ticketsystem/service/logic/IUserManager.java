package ticketsystem.service.logic;

import ticketsystem.service.model.data.UserDTO;

public interface IUserManager {
    UserDTO login(UserDTO user);
    boolean register(UserDTO user);
    int getRoleFromUser(UserDTO user);
}
