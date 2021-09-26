package com.mad.mad_app;

public class Match {

    String mid, mround, mteamA, mteamB, mtid, oid, mtime;

    public Match(){

    }
    //Constructor
    public Match(String mid, String mround, String mteamA, String mteamB, String mtid, String oid, String mtime) {
        this.mid = mid;
        this.mround = mround;
        this.mteamA = mteamA;
        this.mteamB = mteamB;
        this.mtid = mtid;
        this.oid = oid;
        this.mtime = mtime;
    }

    //Getters and Setters
    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMround() {
        return mround;
    }

    public void setMround(String mround) {
        this.mround = mround;
    }

    public String getMteamA() {
        return mteamA;
    }

    public void setMteamA(String mteamA) {
        this.mteamA = mteamA;
    }

    public String getMteamB() {
        return mteamB;
    }

    public void setMteamB(String mteamB) {
        this.mteamB = mteamB;
    }

    public String getMtid() {
        return mtid;
    }

    public void setMtid(String mtid) {
        this.mtid = mtid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getMtime() {return mtime;}

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }
}





