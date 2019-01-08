package com.mateuszwiorek.budget;

import java.util.ArrayList;
import java.util.List;


public class MoneyList {

    public List<Money> moneyList = new ArrayList<Money>();
    public List<Money> getMoneyList() {
        return moneyList;
    }
    public void setmoneyList(List<Money> moneyList){
        this.moneyList=moneyList;
    }

    public void addtToMoneyList(Money money){
        this.moneyList.add(money);
    }
}