package com.mateuszwiorek.budget;
import java.util.LinkedList;
import java.util.List;


public class UsersList {
    Users user1= new Users("Pablo", "lubieplacki123");
    Users user2= new Users( "Matthew", "12345");
    Users user3= new Users("Kate", "Ewe123");
    Users user4= new Users("Marcela", "ja15");
    public  List<Users> usersList = new LinkedList<Users>();

    UsersList(){
        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);
        usersList.add(user4);
    }
    }
