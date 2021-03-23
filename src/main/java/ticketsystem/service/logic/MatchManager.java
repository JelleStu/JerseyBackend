package ticketsystem.service.logic;

import ticketsystem.service.CustomApplicationConfig;
import ticketsystem.service.factory.TeamManagerFactory;
import ticketsystem.service.model.data.MatchDTO;
import ticketsystem.service.model.data.TeamDTO;
import ticketsystem.service.model.view.MatchView;
import ticketsystem.service.repository.match.IMatchContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MatchManager implements IMatchManager {

    IMatchContext context;
    ITeamManager teamManager = TeamManagerFactory.CreateTeamManager(CustomApplicationConfig.DATA_ENVIRONMENTTEST);


    public MatchManager(IMatchContext context) {
        this.context = context;
    }

    public List<MatchView> getMatches() {
        return convertListToMatchViewList(context.getMatches());
    }

    public MatchView getMatchById(int id) {

        return convertToMatchView(context.getMatchByID(id));
    }

    public List<MatchView> getMatchesByHomeTeam(String name) {
        return convertListToMatchViewList(context.getMatchesByHomeTeam(name));
    }

    public List<MatchView> getMatchesByAwayTeam(String name) {
        return convertListToMatchViewList(context.getMatchesByAwayTeam(name));
    }

    public List<MatchView> getMatchesByTeam(String name) {
        //Convert name to teamid
        TeamDTO team = teamManager.getTeamByName(name);
        if (team != null) {
            return convertListToMatchViewList(context.getMatchesByTeam(team.getID()));
        }
        return Collections.emptyList();
    }

    public List<MatchView> getMatchesByTeamId(int teamid) {
        return convertListToMatchViewList(context.getMatchesByTeamId(teamid));
    }

    public boolean createMatch(MatchView match) {
        try {
            return context.createMatch(convertToMatchDTo(match));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteMatch(MatchView match) {
        try {
            context.deleteMatch(convertToMatchDTo(match));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private MatchView convertToMatchView(MatchDTO match) {
        Date d = match.getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = dateFormat.format(d);

        LocalTime t = match.getTime();
        String stringTime = t.toString();


        return new MatchView(match.getMatchID(), teamManager.getTeamById(match.getHomeTeam()).getName(), teamManager.getTeamById(match.getAwayTeam()).getName(), stringTime, stringDate, match.getScoreHomeTeam(), match.getScoreAwayTeam());
    }

    private List<MatchView> convertListToMatchViewList(List<MatchDTO> matchDTOList){
       List<MatchView> matchViewList = new ArrayList<>();
        for (MatchDTO matchDTO: matchDTOList){
            matchViewList.add(convertToMatchView(matchDTO));
        }
        return matchViewList;
    }

    private MatchDTO convertToMatchDTo(MatchView matchView) throws ParseException {
        String sTime = matchView.getTime();
        LocalTime time = LocalTime.parse(sTime);

        String sDate = matchView.getDate();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

        return new MatchDTO(matchView.getMatchID(), teamManager.getTeamByName(matchView.getHomeTeam()).getID(), teamManager.getTeamByName(matchView.getAwayTeam()).getID(),time, date, matchView.getScoreHomeTeam(), matchView.getScoreHomeTeam());
    }






}
