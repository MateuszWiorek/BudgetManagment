package com.mateuszwiorek.budget;
import java.util.LinkedList;
import java.util.List;

/**
 * klasa zawierająca listę obieków  typu Users
 */
public class UsersList {
    Users user1= new Users("Paweł", "lubieplacki123");
    Users user2= new Users( "Mateusz", "12345");
    Users user3= new Users("Filip", "Ewe123");
    Users user4= new Users("Ewelina", "ja15");
    public  List<Users> usersList = new LinkedList<Users>();
    /**
     * konstruktor UsersList tworzy listę użytkowników, których używamy w aplikacji
     */
    UsersList(){
        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);
        usersList.add(user4);
    }
    }
