package com.fpadilha.typesum.model;

import org.androidannotations.annotations.EBean;

/**
 * Created by fpadilha on 30/06/2015.
 */
@EBean(scope = EBean.Scope.Singleton)
public class User {
    private String id;
    private String password;
    private String regId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }
}

