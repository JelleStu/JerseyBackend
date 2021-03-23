package ticketsystem.service.repository;

import ticketsystem.service.model.data.MatchDTO;
import ticketsystem.service.model.data.RoleDTO;
import ticketsystem.service.model.data.TeamDTO;
import ticketsystem.service.model.data.UserDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DataStore {
    private final List<TeamDTO> teamList = new ArrayList<>();
    private final List<MatchDTO> matchList = new ArrayList<>();
    private final List<UserDTO> userList = new ArrayList<>();
    private final List<RoleDTO> roleList = new ArrayList<>();

    public DataStore(){
        TeamDTO ajax = new TeamDTO(1,"Ajax");
        TeamDTO psv = new TeamDTO(2,"PSV");
        TeamDTO rjc = new TeamDTO(3,"Roda JC");
        TeamDTO fcu = new TeamDTO(4, "Fc Utrecht");
        teamList.add(ajax);
        teamList.add(psv);
        teamList.add(rjc);
        teamList.add(fcu);

        LocalTime time = null;
        Date date = null;
        try {
            time = LocalTime.parse("17:45:00");
            date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        matchList.add(new MatchDTO(1, 1, 4, time, date, 1,0 ));
        matchList.add(new MatchDTO(2, 1, 2, time, date, 1,0 ));
        userList.add(new UserDTO("test", "test", 1));
        userList.add(new UserDTO("joris", "joris", 0));
        roleList.add(new RoleDTO(0, "user"));
        roleList.add(new RoleDTO(1, "admin"));
    }

    public List<TeamDTO> getTeamList(){
        return teamList;
    }

    public TeamDTO getTeam(String name){
        return teamList.stream().filter(t -> name.equals(t.getName())).findFirst().orElse(null);
    }

    public TeamDTO getTeamById(int id){
        return teamList.stream().filter(t -> t.getID() == id).findFirst().orElse(null);
    }

    public boolean deleteTeam(int id){
        TeamDTO t = teamList.stream().filter(team -> team.getID() == id).findFirst().orElse(null);
        if (t == null){
            return false ;
        }
        teamList.remove(t);
        return true;
    }

    public boolean createTeam(TeamDTO team){
        if (this.getTeam(team.getName()) == null){
            teamList.add(team);
            return true;
        }
        return false;
    }

    public boolean updateTeam(int teamID, TeamDTO team){
    TeamDTO old = this.getTeamById(teamID);
    if (old == null){
        return false;
    }
    old.setName(team.getName());
    return true;
    }

    /* Match things */

    public MatchDTO getMatch(int matchID){
        return matchList.stream().filter(match -> match.getMatchID() == matchID).findFirst().orElse(null);
    }

    public List<MatchDTO> getMatchList() {
        return matchList;
    }

    public List<MatchDTO> getMatchHomeTeam(String name){
        List<MatchDTO> filteredList = new ArrayList<>();
        for (MatchDTO match : matchList){
            if (getTeamById(match.getHomeTeam()).getName().equals(name)){
                filteredList.add(match);
            }
        }
        return filteredList;
    }

    public List<MatchDTO> getMatchesByAwayTeam(String name){
        return matchList.stream().filter(m -> getTeamById(m.getAwayTeam()).getName().equals(name)).collect(Collectors.toList());
    }

    public boolean createMatch(MatchDTO match){
        matchList.add(match);
        return matchList.contains(match);
    }

    public void deleteMatch(MatchDTO match){
        matchList.removeIf(m -> m.getMatchID() == match.getMatchID());
    }

    /* user stuff */
    public UserDTO Login(UserDTO user){
        return userList.stream().filter(u -> u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())).findFirst().orElse(null);
    }

    public boolean register(UserDTO user){
        userList.add(user);
        return true;
    }

    public UserDTO getUserByID(int id){
        return userList.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    /* roles */
    public RoleDTO getRoleByID(int id){
        return roleList.stream().filter(i -> i.getRoleID() == id).findFirst().orElse(null);
    }

    public List<RoleDTO> getRoles(){
        return roleList;
    }
}
