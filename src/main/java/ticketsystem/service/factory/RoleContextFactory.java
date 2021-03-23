package ticketsystem.service.factory;

import ticketsystem.service.repository.role.IRoleConext;
import ticketsystem.service.repository.role.RoleHibernateContext;
import ticketsystem.service.repository.role.RoleSystemContext;

public class RoleContextFactory {
    public static IRoleConext CreateContext(String type){
        if (type.equals("Memory")){
            return new RoleSystemContext();
        }
        return new RoleHibernateContext();
    }
}
