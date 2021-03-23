package ticketsystem.service.factory;

import ticketsystem.service.CustomApplicationConfig;
import ticketsystem.service.logic.IRoleManager;
import ticketsystem.service.logic.RoleManager;
import ticketsystem.service.repository.role.IRoleConext;

public class RoleManagerFactory {

    public static IRoleManager createRoleManger(){
        IRoleConext context = RoleContextFactory.CreateContext(CustomApplicationConfig.DATA_ENVIRONMENT);
        return new RoleManager(context);
    }
}
