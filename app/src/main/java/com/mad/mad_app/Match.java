package com.mad.mad_app;

public class Match {

    String id, mteamA, mteamB, mtime, mround;

    public Match(){

    }

    public Match(String id, String mteamA, String mteamB, String mtime, String mround){

        this.id = id;
        this.mteamA = mteamA;
        this.mteamB = mteamB;
        this.mtime = mtime;
        this.mround = mround;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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







