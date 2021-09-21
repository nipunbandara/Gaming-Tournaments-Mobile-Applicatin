package com.mad.mad_app;

public class Tournament {
    String id, tdate, tmaxteams, tname, tselectedgame, ttime, ttype;

    public Tournament(){

    }

    public Tournament(String id, String tdate, String tmaxteams, String tname, String tselectedgame, String ttime, String ttype) {
        this.id = id;
        this.tdate = tdate;
        this.tmaxteams = tmaxteams;
        this.tname = tname;
        this.tselectedgame = tselectedgame;
        this.ttime = ttime;
        this.ttype = ttype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public String getTmaxteams() {
        return tmaxteams;
    }

    public void setTmaxteams(String tmaxteams) {
        this.tmaxteams = tmaxteams;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTselectedgame() {
        return tselectedgame;
    }

    public void setTselectedgame(String tselectedgame) {
        this.tselectedgame = tselectedgame;
    }

    public String getTtime() {
        return ttime;
    }

    public void setTtime(String ttime) {
        this.ttime = ttime;
    }

    public String getTtype() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype;
    }
}
