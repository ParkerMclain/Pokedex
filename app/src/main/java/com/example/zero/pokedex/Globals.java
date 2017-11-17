package com.example.zero.pokedex;

import android.app.Application;

public class Globals extends Application {
    private String password = "";

    //0 = No password
    //1 = Simple password
    //2 = Complex password
    private int passwordType = -1;

    public void setPasswordType(int type) {
        this.passwordType = type;
    }

    public int getPasswordType() {
        return this.passwordType;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getPassword() {
        return this.password;
    }

}
