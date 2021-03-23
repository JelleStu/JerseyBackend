package ticketsystem.service.repository.match;

import ticketsystem.service.model.data.MatchDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchHibernateContext implements IMatchContext {

    EntityManager manager;

    public MatchHibernateContext(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.ticketsystem.jpa");
        manager = entityManagerFactory.createEntityManager();
    }

    public List<MatchDTO> getMatches() {
        return manager.createNativeQuery("SELECT * FROM match", MatchDTO.class).getResultList();
    }

    public MatchDTO getMatchByID(int id) {
        return manager.find(MatchDTO.class, id);
    }

    public List<MatchDTO> getMatchesByHomeTeam(String name) {
        Query query;
        query = manager.createNativeQuery("SELECT * FROM match WHERE hometeam = ?1", MatchDTO.class);
        query.setParameter(1, name);

        return query.getResultList();
    }

    public List<MatchDTO> getMatchesByAwayTeam(String name) {
        Query query;
        query = manager.createNativeQuery("SELECT * FROM match WHERE awayteam = ?1", MatchDTO.class);
        query.setParameter(1, name);

        return query.getResultList();
    }

    public List<MatchDTO> getMatchesByTeam(int teamid) {
        Query query;
        query = manager.createNativeQuery("SELECT * FROM match WHERE hometeam = ?1 OR awayteam = ?2", MatchDTO.class);
        query.setParameter(1, teamid);
        query.setParameter(2, teamid);

        return query.getResultList();
    }

    public List<MatchDTO> getMatchesByTeamId(int teamid) {
        List<MatchDTO> matchList;
        Query query;
        query = manager.createNativeQuery("SELECT * FROM match WHERE hometeam=?1 OR awayteam=?2", MatchDTO.class);
        query.setParameter(1, teamid);
        query.setParameter(2, teamid);

        matchList = query.getResultList();
        if (matchList.isEmpty()){
            return Collections.emptyList();
        }
        return matchList;
    }

    public boolean createMatch(MatchDTO match) {
        manager.getTransaction().begin();
        manager.persist(match);
        manager.flush();
        manager.getTransaction().commit();
        return true;
    }

    public void deleteMatch(MatchDTO match) {
        manager.getTransaction().begin();
        manager.remove(match);
        manager.flush();
        manager.getTransaction().commit();
    }
}
