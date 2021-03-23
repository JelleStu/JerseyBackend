package ticketsystem.service.repository.role;

import ticketsystem.service.model.data.RoleDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class RoleHibernateContext implements IRoleConext {

    EntityManager manager;

    public RoleHibernateContext(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.ticketsystem.jpa");
        manager = entityManagerFactory.createEntityManager();
    }

    public List<RoleDTO> getRoles() {
        return manager.createNativeQuery("SELECT * FROM roles", RoleDTO.class).getResultList();
    }

    public RoleDTO getRolesByID(int id) {
        return manager.find(RoleDTO.class, id);
    }
}
