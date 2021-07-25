package com.boha.myweb3.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Generated("jsonschema2pojo")
public class WalletFile {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("crypto")
    @Expose
    private Crypto crypto;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Crypto getCrypto() {
        return crypto;
    }

    public void setCrypto(Crypto crypto) {
        this.crypto = crypto;
    }

}
