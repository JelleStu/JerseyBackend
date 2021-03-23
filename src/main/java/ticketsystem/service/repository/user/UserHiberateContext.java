package ticketsystem.service.repository.user;

import ticketsystem.service.model.data.UserDTO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

public class UserHiberateContext implements IUserContext {
    EntityManager manager;

    public UserHiberateContext(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.ticketsystem.jpa");
        manager = entityManagerFactory.createEntityManager();
    }

    public UserDTO getUserByID(int id) {
        return manager.find(UserDTO.class, id);
    }

    public UserDTO login(UserDTO user) {
        Query query;
        query = manager.createNativeQuery("SELECT * FROM users WHERE username = ?1 AND password = ?2", UserDTO.class);
        query.setParameter(1, user.getUsername());
        query.setParameter(2, user.getPassword());

        Object u;
        u = query.getResultList().get(0);
        return (UserDTO) u;
    }

    public int getRoleFromUser(UserDTO user){
        Query query;
        query = manager.createNativeQuery("SELECT * FROM users WHERE id = ?1", UserDTO.class);
        query.setParameter(1, user.getId());

        UserDTO u = (UserDTO) query.getResultList().get(0);
        return u.getId();
    }

    @Transactional
    public boolean register(UserDTO user) {
            manager.getTransaction().begin();
            manager.persist(user);
            manager.flush();
            manager.getTransaction().commit();
            return true;
    }
}
