package com.boha.myweb3.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Crypto {

    @SerializedName("cipher")
    @Expose
    private String cipher;
    @SerializedName("ciphertext")
    @Expose
    private String ciphertext;
    @SerializedName("cipherparams")
    @Expose
    private Cipherparams cipherparams;
    @SerializedName("kdf")
    @Expose
    private String kdf;
    @SerializedName("kdfparams")
    @Expose
    private Kdfparams kdfparams;
    @SerializedName("mac")
    @Expose
    private String mac;

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public Cipherparams getCipherparams() {
        return cipherparams;
    }

    public void setCipherparams(Cipherparams cipherparams) {
        this.cipherparams = cipherparams;
    }

    public String getKdf() {
        return kdf;
    }

    public void setKdf(String kdf) {
        this.kdf = kdf;
    }

    public Kdfparams getKdfparams() {
        return kdfparams;
    }

    public void setKdfparams(Kdfparams kdfparams) {
        this.kdfparams = kdfparams;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

}
