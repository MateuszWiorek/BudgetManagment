 package com.mateuszwiorek.budget;


 class Users {

    public static String currentLoggedUser;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    Users(String name, String password){
        this.username= name;
        this.password= password;
    }

    public String getPassword() {
        return password;
    }

    public boolean comparingLoggingData(Users user){
        if (user.getUsername().equals(username)&& user.getPassword().equals(password)) {
            this.currentLoggedUser = user.getUsername();
            return true;
        }else
            return false;
    }

}
