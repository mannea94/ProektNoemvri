package com.example.manne.proektnoemvri;

import java.io.Serializable;

/**
 * Created by manne on 25.11.2017.
 */

public class User implements Serializable {
    public String ime;
    public String lastName;
    public String userName;
    public char gender;

    public User(){
        this.userName = "guest";
        ime = "Guest";
        lastName="Guest";
        gender='M';
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getIme() {
        return ime;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;

    }

//    public void setGender(boolean gender) {
//        this.gender = gender;
//    }
//
//    public boolean isGender() {
//        return gender;
//    }


    public void setGender(char gender) {
        this.gender = gender;
    }

    public char getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return userName;
    }
}


