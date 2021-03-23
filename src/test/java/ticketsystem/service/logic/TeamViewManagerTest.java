package ticketsystem.service.logic;

import junit.framework.TestCase;
import ticketsystem.service.CustomApplicationConfig;
import ticketsystem.service.factory.TeamManagerFactory;
import ticketsystem.service.model.data.TeamDTO;

import java.util.ArrayList;
import java.util.List;

public class TeamViewManagerTest extends TestCase {
    ITeamManager teamManager = TeamManagerFactory.CreateTeamManager(CustomApplicationConfig.DATA_ENVIRONMENTTEST);

    public void testGetTeams() {
        //Arrange
        List<TeamDTO> teamList = new ArrayList<>();
        teamList.add(new TeamDTO(1,"Ajax"));
        teamList.add(new TeamDTO(2,"PSV"));
        teamList.add(new TeamDTO(3,"Roda JC"));
        teamList.add(new TeamDTO(4, "Fc Utrecht"));

        //Action
        List<TeamDTO> teamListDatastore = teamManager.getTeams();

        //Assert
        for (int i = 0; i < teamList.size(); i++) {
            assertEquals(teamList.get(i).getID(), teamListDatastore.get(i).getID());
            assertEquals(teamList.get(i).getName(), teamListDatastore.get(i).getName());
        }
    }

    public void testGetTeamById() {
        //Arrange
        TeamDTO team = new TeamDTO(1, "Ajax");

        //Action
        TeamDTO teamDatastore = teamManager.getTeamById(team.getID());

        //Assert
        assertEquals(team.getID(), teamDatastore.getID());
        assertEquals(team.getName(), teamDatastore.getName());
    }

    public void testGetTeamByName() {
        //Arrange
        TeamDTO team = new TeamDTO(2, "PSV");

        //Action
        TeamDTO teamDatastore = teamManager.getTeamByName(team.getName());

        //Assert
        assertEquals(team.getID(), teamDatastore.getID());
        assertEquals(team.getName(), teamDatastore.getName());
    }

    public void testCreateTeam() {
        //Arrange
        TeamDTO team = new TeamDTO(5, "TestTeam");
        boolean added = false;
        //Action
        try {
            added = teamManager.createTeam(team);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TeamDTO teamDatastore = teamManager.getTeamById(5);

        //Assert
        assertTrue(added);
        assertEquals(team.getID(), teamDatastore.getID());
        assertEquals(team.getName(), teamDatastore.getName());
    }

    public void testDeleteTeam() {
        //Arrange
        TeamDTO team = new TeamDTO(5, "TestTeam");

        //Action
        teamManager.deleteTeam(team.getID());
        List<TeamDTO> teamList = teamManager.getTeams();

        //Assert
        assertEquals(4, teamList.size());

    }

    public void testUpdateTeam() {
        //Arrange
        //Teams what needs to be updated
        //Team 1 name will be updated to TestUpdate
        TeamDTO team = new TeamDTO(1, "TestUpdate");
        boolean updated = false;

        //Action
        updated = teamManager.updateTeam(team);
        TeamDTO teamUpdated = teamManager.getTeamById(1);

        assertTrue(updated);
        assertEquals(team.getName(), teamUpdated.getName());


        //Cleanup
        TeamDTO t = new TeamDTO(1, "Ajax");
        teamManager.updateTeam(t);
    }
}