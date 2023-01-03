package com.example.subscription_module.subscriberapi_main;


public class Utility {
    private static String username;
    private static String password;

    public static void setCredentials(String passedusername,String passedpassword){
       username=passedusername;
       password=passedpassword;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Utility.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Utility.password = password;
    }

    public Utility() {
    }
    
}
