package ticketsystem.service.repository.role;

import ticketsystem.service.model.data.RoleDTO;

import java.util.List;

public interface IRoleConext {
    List<RoleDTO> getRoles();
    RoleDTO getRolesByID(int id);
}
