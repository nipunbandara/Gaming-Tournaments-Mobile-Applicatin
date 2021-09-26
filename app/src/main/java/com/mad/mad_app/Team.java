package com.mad.mad_app;

public class Team {
    String oid, tdesc, tid, tmname;

    public Team() {

    }

    public Team(String oid, String tdesc, String tid, String tmname) {
        this.oid = oid;
        this.tdesc = tdesc;
        this.tid = tid;
        this.tmname = tmname;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getTdesc() {
        return tdesc;
    }

    public void setTdesc(String tdesc) {
        this.tdesc = tdesc;
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

    public void setTmname(String tmname) {
        this.tmname = tmname;
    }
}

