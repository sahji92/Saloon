package com.dpk.saloon.model;

import java.io.Serializable;

public class StoreUser implements Serializable {
    private  String usrname;
    private boolean isCreated,isNew;
    private boolean isAuthenticated;
    private String uid;
    private String saloonName;
    private String saloonId;
    private String location;
    private String password;
    public StoreUser(boolean isCreated, boolean isNew, boolean isAuthenticated, String uid, String saloonName, String saloonId, String location, String password) {
        this.isCreated = isCreated;
        this.isNew = isNew;
        this.isAuthenticated = isAuthenticated;
        this.uid = uid;
        this.saloonName = saloonName;
        this.saloonId = saloonId;
        this.location = location;
        this.password = password;
    }
    public StoreUser(){

    }

    public StoreUser(String uid, String saloonId, String usrname) {
        this.uid=uid;
        this.saloonId=saloonId;
        this.usrname=usrname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSaloonName() {
        return saloonName;
    }

    public void setSaloonName(String saloonName) {
        this.saloonName = saloonName;
    }

    public String getSaloonId() {
        return saloonId;
    }

    public void setSaloonId(String saloonId) {
        this.saloonId = saloonId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public boolean isCreated() {
        return isCreated;
    }

    public void setCreated(boolean created) {
        isCreated = created;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }
}
