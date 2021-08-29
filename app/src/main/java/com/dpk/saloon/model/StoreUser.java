package com.dpk.saloon.model;

import java.io.Serializable;
import java.util.ArrayList;

public class StoreUser implements Serializable {
    private  String usrname;
    private String uid;
    private String saloonName;
    private String saloonId;
    private String password;
    private ArrayList<String> storeServices= new ArrayList<>();
    public StoreUser(){

    }

    public StoreUser(String uid, String usrname, String storeName) {
        this.uid=uid;
        this.usrname=usrname;
        this.saloonName=storeName;
    }

    public StoreUser(String storeId, String pswd) {
        this.saloonId=storeId;
        this.password=pswd;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public ArrayList<String> getStoreServices() {
        return storeServices;
    }

    public void setStoreServices(ArrayList<String> storeServices) {
        this.storeServices = storeServices;
    }
}
