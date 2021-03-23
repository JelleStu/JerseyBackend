package ticketsystem.service.repository.role;

import ticketsystem.service.model.data.RoleDTO;
import ticketsystem.service.repository.DataStore;

import java.util.Collections;
import java.util.List;

public class RoleSystemContext implements IRoleConext {

    DataStore datastore = new DataStore();
    public List<RoleDTO> getRoles() {
        return datastore.getRoles();
    }

    public RoleDTO getRolesByID(int id) {
        return datastore.getRoleByID(id);
    }
}
