
package com.mad.mad_app;

public class Game {
    String  gdescription;
    String gmaxteams;
    String gminteams;
    String gname;
    String gregion;
    String id ;
    String oid;

    public Game( String gdescription, String gmaxteams, String gminteams, String gname, String gregion, String id,String oid ){
        this.gdescription = gdescription;
        this.gmaxteams = gmaxteams;
        this.gminteams = gminteams;
        this.gname = gname;
        this.gregion = gregion;
        this.id = id;
        this.oid = oid;

    }


    public Game() {

    }

    public String getGdescription() {
        return gdescription;
    }

    public void setGdescription(String gdescription) {
        this.gdescription = gdescription;
    }

    public String getGmaxteams() {
        return gmaxteams;
    }

    public void setGmaxteams(String gmaxteams) {
        this.gmaxteams = gmaxteams;
    }

    public String getGminteams() {
        return gminteams;
    }

    public void setGminteams(String gminteams) {
        this.gminteams = gminteams;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGregion() {
        return gregion;
    }

    public void setGregion(String gregion) {
        this.gregion = gregion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}


