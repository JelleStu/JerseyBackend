package ticketsystem.service.repository.user;

import ticketsystem.service.model.data.UserDTO;

public interface IUserContext {
    UserDTO getUserByID(int id);
    UserDTO login(UserDTO user);
    boolean register(UserDTO user);
    int getRoleFromUser(UserDTO user);
}
