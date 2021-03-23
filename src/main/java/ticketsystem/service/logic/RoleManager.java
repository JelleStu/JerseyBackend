package ticketsystem.service.logic;

import ticketsystem.service.repository.role.IRoleConext;

public class RoleManager implements IRoleManager {

    IRoleConext context;

    public RoleManager(IRoleConext context){
        this.context = context;
    }

    public String getRole(int id) {
        return context.getRolesByID(id).getName();
    }
}
