package com.mateuszwiorek.budget;

import java.util.ArrayList;
import java.util.List;


public class MoneyList {

    public List<Money> moneyList = new ArrayList<Money>();

    public void addtToMoneyList(Money money){
        this.moneyList.add(money);
    }
}