 package com.mateuszwiorek.budget;

 /**
  * Klasa przetrzymujaca dane uzytkownika
  */
 class Users {
     /**
      * uzywane do wyszukiwania w bazie danych po nazwie uzytkownika
      */
    public static String currentLoggedUser;
     /**
      * nazwa uzytkownika
      */
    private String username;
     /**
      * haslo
      */
    private String password;

     /**
      *
      * @return username, zwraca nazwę użytkownikaa
      */
    public String getUsername() {
        return username;
    }

     /**
      *
      * @param name ustala pole username nowo stworzonego obiektu na podane przez użytkownika
      * @param password ustala pole password nowo utworzonego obiektu na podane przez użytkownika
      * konstruktor tworzy obiekt o podanej nazwie i hasla
      */
    Users(String name, String password){
        this.username= name;
        this.password= password;
    }

    public String getPassword() {
        return password;
    }

     /**
      *
      * @param user przyjmuje argument klasy Users
      * @return zwraca prawdę lub fałsz w zależności od tego czy dane podane przez użytkownika zgadzają się z danymi klasy, jeśli tak ustawia wartość CurrentlyLoggedUser na zalogowanego użytkownika
      */
    public boolean comparingLoggingData(Users user){
        if (user.getUsername().equals(username)&& user.getPassword().equals(password)) {
            this.currentLoggedUser = user.getUsername();
            return true;
        }else
            return false;
    }

}
