package com.example.myapplication.apimodels.citymodel;

public class Cases {
    String vi;
    String tv;
    String ro;
    String pr;
    String da;

    public Cases(String vi, String tv, String ro, String pr, String da) {
        this.vi = vi;
        this.tv = tv;
        this.ro = ro;
        this.pr = pr;
        this.da = da;
    }

    public String getVi() {
        return vi;
    }

    public void setVi(String vi) {
        this.vi = vi;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getRo() {
        return ro;
    }

    public void setRo(String ro) {
        this.ro = ro;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public String getDa() {
        return da;
    }

    public void setDa(String da) {
        this.da = da;
    }
}
