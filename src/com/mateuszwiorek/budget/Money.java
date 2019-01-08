package com.mateuszwiorek.budget;

import java.util.Date;

public class Money {
    private int id;
    private String name;
    private int value;
    private Date date;
    private String user;
    private String moneyType;

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public String getDate() {
        return date.toString();
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String toString(){
        return getId() + "," + getName() +","+ getValue()+ "," + getDate() +"," + getUser() + ", "+getMoneyType();
    }

}
