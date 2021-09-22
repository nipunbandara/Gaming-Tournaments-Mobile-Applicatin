package com.mad.mad_app;

public class Match {

    String oid, id, mteamA, mteamB, mtime, mround;

    public Match(){

    }

    public Match(String oid, String id, String mteamA, String mteamB, String mround, String mtime){

        this.oid = oid;
        this.id = id;
        this.mteamA = mteamA;
        this.mteamB = mteamB;
        this.mround = mround;
        this.mtime = mtime;
    }


    public String getmid() {
        return id;
    }

    public void setmid(String id) {
        this.id = id;
    }

    public String getoid() {
        return oid;
    }

    public void setoid(String oid) {
        this.oid = oid;
    }

    public String getmteamA() {
        return mteamA;
    }

    public void setmteamA(String mteamA) {
        this.mteamA = mteamA;
    }

    public String getmteamB() {
        return mteamB;
    }

    public void setmteamB(String mteamB) {
        this.mteamB = mteamB;
    }

    public String getmtime() {
        return mtime;
    }

    public void setmtime(String mtime) {
        this.mtime = mtime;
    }

    public String getmround() {
        return mround;
    }

    public void setmround(String mround) {
        this.mround = mround;
    }

}







