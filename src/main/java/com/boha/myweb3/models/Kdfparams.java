package com.boha.myweb3.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Kdfparams {

    @SerializedName("dklen")
    @Expose
    private Integer dklen;
    @SerializedName("n")
    @Expose
    private Integer n;
    @SerializedName("p")
    @Expose
    private Integer p;
    @SerializedName("r")
    @Expose
    private Integer r;
    @SerializedName("salt")
    @Expose
    private String salt;

    public Integer getDklen() {
        return dklen;
    }

    public void setDklen(Integer dklen) {
        this.dklen = dklen;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
