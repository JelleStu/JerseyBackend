package ticketsystem.service.model.view;

public class MatchView {
    private int matchID;

    private String homeTeam;
    private String awayTeam;

    private String time;

    private String date;

    private int scoreHomeTeam;

    private int scoreAwayTeam;

    public MatchView() {
    }

    public MatchView(int matchID, String homeTeam, String awayTeam, String time, String date, int scoreHomeTeam, int scoreAwayTeam) {
        this.matchID = matchID;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.time = time;
        this.date = date;
        this.scoreHomeTeam = scoreHomeTeam;
        this.scoreAwayTeam = scoreAwayTeam;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScoreHomeTeam() {
        return scoreHomeTeam;
    }

    public void setScoreHomeTeam(int scoreHomeTeam) {
        this.scoreHomeTeam = scoreHomeTeam;
    }

    public int getScoreAwayTeam() {
        return scoreAwayTeam;
    }

    public void setScoreAwayTeam(int scoreAwayTeam) {
        this.scoreAwayTeam = scoreAwayTeam;
    }
}


