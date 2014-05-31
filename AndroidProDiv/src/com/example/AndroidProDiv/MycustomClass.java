package com.example.AndroidProDiv;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-31
 * Time: обнГ3:47
 * To change this template use File | Settings | File Templates.
 */
public class MycustomClass implements Serializable {
    public static final long serializableVersion = 1L;
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
