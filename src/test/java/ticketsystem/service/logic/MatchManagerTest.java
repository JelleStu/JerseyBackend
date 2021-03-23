package ticketsystem.service.logic;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ticketsystem.service.CustomApplicationConfig;
import ticketsystem.service.factory.MatchManagerFactory;
import ticketsystem.service.model.view.MatchView;

import java.util.ArrayList;
import java.util.List;

public class MatchManagerTest extends TestCase {
    private final IMatchManager matchManager = MatchManagerFactory.createMatchManager(CustomApplicationConfig.DATA_ENVIRONMENTTEST);

    @Test
    @Order(1)
    public void testGetMatches() {
        //Arrange
        //We need a empty list to fill
        List<MatchView> matchList;

        //Act get all matches
        matchList = matchManager.getMatches();

        //Because the datastore already contains 2 matches we assert on the count of 2.
        Assert.assertEquals(2, matchList.size());
    }

    @Test
    @Order(2)
    public void testGetMatchById() {
        //Arrange
        //We create an identical match.
        MatchView match = new MatchView(1,"Ajax", "Fc Utrecht", "17:45", "2020-12-08", 1, 0);


        //Act
        MatchView matchDatastore = matchManager.getMatchById(match.getMatchID());

        //Assert
        Assert.assertEquals(match.getMatchID(), matchDatastore.getMatchID());
        Assert.assertEquals(match.getHomeTeam(), matchDatastore.getHomeTeam());
        Assert.assertEquals(match.getAwayTeam(), matchDatastore.getAwayTeam());
        Assert.assertEquals(match.getTime(), matchDatastore.getTime());
        Assert.assertEquals(match.getDate(), matchDatastore.getDate());
        Assert.assertEquals(match.getScoreHomeTeam(), matchDatastore.getScoreHomeTeam());
        Assert.assertEquals(match.getScoreAwayTeam(), matchDatastore.getScoreAwayTeam());
    }

    @Test
    @Order(3)
    public void testGetMatchesByHomeTeam() {
        //Arrange
        //Empty list to fill
        List<MatchView> matchList = new ArrayList<>();
        //Create identical matches
        MatchView match1 = new MatchView(1,"Ajax", "Fc Utrecht", "17:45", "2020-12-08", 1, 0);
        MatchView match2 = new MatchView(2,"Ajax", "PSV", "17:45", "2020-12-08", 1, 0);
        matchList.add(match1);
        matchList.add(match2);


        //Act
        List<MatchView> matchListDatastore = matchManager.getMatchesByHomeTeam("Ajax");

        //Assert
        for (int i =0; i < matchListDatastore.size(); i++) {
            Assert.assertEquals(matchListDatastore.get(i).getMatchID(), matchList.get(i).getMatchID());
            Assert.assertEquals(matchListDatastore.get(i).getHomeTeam(), matchList.get(i).getHomeTeam());
            Assert.assertEquals(matchListDatastore.get(i).getAwayTeam(), matchList.get(i).getAwayTeam());
            Assert.assertEquals(matchListDatastore.get(i).getTime(), matchList.get(i).getTime());
            Assert.assertEquals(matchListDatastore.get(i).getDate(), matchList.get(i).getDate());
            Assert.assertEquals(matchListDatastore.get(i).getScoreHomeTeam(), matchList.get(i).getScoreHomeTeam());
            Assert.assertEquals(matchListDatastore.get(i).getScoreAwayTeam(), matchList.get(i).getScoreAwayTeam());
        }
    }

    @Test
    @Order(4)
    public void testGetMatchesByAwayTeam() {
        //Arrange
        //Empty list to fill
        List<MatchView> matchList = new ArrayList<>();
        //Create identical matches
        matchList.add(new MatchView(2,"Ajax", "PSV", "17:45", "2020-12-08", 1, 0));


        //Act
        List<MatchView> matchListDatastore = matchManager.getMatchesByAwayTeam("PSV");

        //Assert
        for (int i =0; i < matchListDatastore.size(); i++) {
            Assert.assertEquals(matchListDatastore.get(i).getMatchID(), matchList.get(i).getMatchID());
            Assert.assertEquals(matchListDatastore.get(i).getHomeTeam(), matchList.get(i).getHomeTeam());
            Assert.assertEquals(matchListDatastore.get(i).getAwayTeam(), matchList.get(i).getAwayTeam());
            Assert.assertEquals(matchListDatastore.get(i).getTime(), matchList.get(i).getTime());
            Assert.assertEquals(matchListDatastore.get(i).getDate(), matchList.get(i).getDate());
            Assert.assertEquals(matchListDatastore.get(i).getScoreHomeTeam(), matchList.get(i).getScoreHomeTeam());
            Assert.assertEquals(matchListDatastore.get(i).getScoreAwayTeam(), matchList.get(i).getScoreAwayTeam());
        }

    }

    @Test
    @Order(5)
    public void testCreateAndDelete(){
        //Arrange
        boolean added;
        int sizeBefore = matchManager.getMatches().size();
        int sizeAfter = 3;

        //Create new match
        MatchView match = new MatchView(3,"Ajax", "Roda JC", "17:45", "2020-12-08", 1, 0);

        //Act
        added = matchManager.createMatch(match);

        //Assert
        Assert.assertTrue(added);
        Assert.assertNotSame(sizeBefore, sizeAfter);
        Assert.assertEquals(sizeAfter, matchManager.getMatches().size());

        //Arrange
        int sizeBeforeDelete = matchManager.getMatches().size();
        int sizeAfterDelete = 2;

        //Act
        matchManager.deleteMatch(match);

        //Assert
        Assert.assertNotSame(sizeBeforeDelete, sizeAfterDelete);
        Assert.assertEquals(sizeAfterDelete, matchManager.getMatches().size());
    }

}