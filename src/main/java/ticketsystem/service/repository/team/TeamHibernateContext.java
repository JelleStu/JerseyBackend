package ticketsystem.service.repository.team;


import ticketsystem.service.model.data.TeamDTO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class TeamHibernateContext  implements  ITeamContext{

EntityManager manager;

    public TeamHibernateContext(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.ticketsystem.jpa");
        manager = entityManagerFactory.createEntityManager();
    }

    public List<TeamDTO> getTeams(){
        manager.getTransaction().begin();
       return manager.createNativeQuery("SELECT * FROM TEAMS", TeamDTO.class).getResultList();
    }


    public TeamDTO getTeamByID(int id) {
        return  manager.find(TeamDTO.class, id);
    }

    public TeamDTO getTeamByName(String name) {
        Query query;
        query = manager.createNativeQuery("SELECT * FROM teams WHERE name LIKE CONCAT('%',?1,'%')", TeamDTO.class);
        query.setParameter(1, name);
        System.out.println("setparam");
        Object team;
        team = query.getResultList().get(0);
        System.out.println("get team");
        if (team == null){
            return null;
        }
        return (TeamDTO) team;
    }

    @Override
    public boolean createTeam(TeamDTO team) {
        manager.getTransaction().begin();
        manager.persist(team);
        manager.flush();
        manager.getTransaction().commit();
        return true;
    }

    public boolean deleteTeam(int id) {
        manager.getTransaction().begin();
        manager.remove(getTeamByID(id));
        manager.flush();
        manager.getTransaction().commit();
        return true;
    }

    public boolean updateTeam(TeamDTO team) {
        manager.getTransaction().begin();
        manager.merge(team);
        manager.flush();
        manager.getTransaction().commit();
        return true;
    }
}
