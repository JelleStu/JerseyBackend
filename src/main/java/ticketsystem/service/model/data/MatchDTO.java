package ticketsystem.service.model.data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "MATCH")
public class MatchDTO {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name="increment", strategy = "increment")
    private int matchID;

    @Column(name = "HOMETEAM")
    private int homeTeam;
    @Column(name = "AWAYTEAM")
    private int awayTeam;

    @Column(name = "TIME")
    private LocalTime time;

    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;


    @Column(name = "SCOREHOME")
    private int scoreHomeTeam;

    @Column(name = "SCOREAWAY")
    private int scoreAwayTeam;

    public MatchDTO(int matchID, int homeTeam, int awayTeam) {
        this.matchID = matchID;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public MatchDTO(int matchID, int homeTeam, int awayTeam, LocalTime time, Date date, int scoreHomeTeam, int scoreAwayTeam) {
        this.matchID = matchID;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.time = time;
        this.date = date;
        this.scoreHomeTeam = scoreHomeTeam;
        this.scoreAwayTeam = scoreAwayTeam;
    }

    public MatchDTO() {
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(int homeTeam) {
        this.homeTeam = homeTeam;
    }

    public int getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(int awayTeam) {
        this.awayTeam = awayTeam;
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


