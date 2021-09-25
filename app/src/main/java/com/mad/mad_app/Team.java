package com.mad.mad_app;

public class Team {
    String oid, tid, tmname, tdesc;

    public Team(){

    }

    public Team(String oid, String tid, String tmname, String tdesc) {
        this.oid = oid;
        this.tid = tid;
        this.tmname = tmname;
        this.tdesc = tdesc;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTmname() {
        return tmname;
    }

    public void setTmname(String tname) {
        this.tmname = tmname;
    }

    public String getTdesc() {
        return tdesc;
    }

    public void setTdesc(String tdesc) {
        this.tdesc = tdesc;
    }
}

