package fr.propan.solveit.entities;

import java.util.Date;

public class GameHistory extends BaseEntity {
    private Integer score;
    private Date date;
    private String username;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "GameHistory{" +
                "score=" + score +
                ", date=" + date +
                ", username='" + username + '\'' +
                '}';
    }
}
