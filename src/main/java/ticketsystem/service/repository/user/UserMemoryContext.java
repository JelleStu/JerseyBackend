package ticketsystem.service.repository.user;

import ticketsystem.service.model.data.UserDTO;
import ticketsystem.service.repository.DataStore;

public class UserMemoryContext implements IUserContext {
    static DataStore dataStore = new DataStore();
    public UserDTO getUserByID(int id) {
        return dataStore.getUserByID(id);
    }

    public UserDTO login(UserDTO user) {
        return dataStore.Login(user);
    }

    public boolean register(UserDTO user) {
        return dataStore.register(user);
    }

    public int getRoleFromUser(UserDTO user) {
        return dataStore.getRoleByID(user.getRole()).getRoleID();
    }
}
