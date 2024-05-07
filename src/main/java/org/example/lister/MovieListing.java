package org.example.lister;

import org.example.movies.Movie;
import org.example.user.User;

import java.util.HashMap;
import java.util.HashSet;

public class MovieListing {

    private HashMap<String, User> users;
    private HashMap<String, Movie> movies;

    public MovieListing(){
        this.movies = new HashMap<>();
        this.users = new HashMap<>();
    }

    public boolean isValidEmail(String email) {
        if (email.length() == 0) {
            return false;
        }

        String[] parts = email.split("@");
        if (parts.length != 2) {
            return false;
        }

        String localPart = parts[0];
        String domainPart = parts[1];

        //Checking local part
        if (localPart.length() == 0 || localPart.length() > 64) {
            return false;
        }

        //Checking domain part
        if (domainPart.length() == 0 || domainPart.length() > 255) {
            return false;
        }

        //Checking for valid characters in local part and domain part
        String validLocalChars = "!#$%&'*+/=?^_`{|}~-";
        String validDomainChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-.";
        for (char ch : localPart.toCharArray()) {
            if (!Character.isLetterOrDigit(ch) && validLocalChars.indexOf(ch) == -1) {
                return false;
            }
        }
        for (char ch : domainPart.toCharArray()) {
            if (!Character.isLetterOrDigit(ch) && validDomainChars.indexOf(ch) == -1) {
                return false;
            }
        }

        //Checking for consecutive dots or leading/trailing dots in domain part
        if (domainPart.startsWith(".") || domainPart.endsWith(".") || domainPart.contains("..")) {
            return false;
        }

        return true;
    }
    public boolean registerUser(String email) {
        if(!isValidEmail(email))
            return false;

        if(!users.containsKey(email)){
            users.put(email, new User(email));
            return true;
        }

        return false;
    }


}