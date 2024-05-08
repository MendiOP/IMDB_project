package org.example.lister;

import org.example.movies.Movie;
import org.example.primarymovies.MovieDatabase;
import org.example.user.User;

import java.util.*;

public class MovieListing {

    private HashMap<String, User> users;
    private HashMap<String, Movie> movies;

    public MovieListing(){
        this.movies = new LinkedHashMap<>();
        this.users = new LinkedHashMap<>();
        MovieDatabase.initialize(this);
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

    //adding movies
    public boolean addMovies(String title, String cast, String category, String date, String budget) {
        movies.put(title, new Movie(title, cast, category,date, budget));

        return true;
    }

    //getting the desired movie details
    public Movie getMovieDetails(String title) {
        if(movies.get((title)) == null)
            return null;

        return movies.get(title);
    }

    //search movies by title
    public ArrayList<Movie> searchByTitle(String title) {
        ArrayList<Movie> movieArrayList = new ArrayList<>();

        for(Movie movie : movies.values()){
            if(movie.getTitle().toLowerCase().contains(title.toLowerCase()))
                movieArrayList.add(movie);
        }

        movieArrayList.sort((m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle()));

        return movieArrayList;
    }

    //search movies by cast name
    public ArrayList<Movie> searchByCast(String cast) {
        ArrayList<Movie> movieArrayList = new ArrayList<>();

        for(Movie movie : movies.values()){
            if(movie.getCast().toLowerCase().contains(cast.toLowerCase()))
                movieArrayList.add(movie);
        }

        movieArrayList.sort((m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle()));

        return movieArrayList;
    }

    //search movies by their category
    public ArrayList<Movie> searchByCategory(String category) {
        ArrayList<Movie> movieArrayList = new ArrayList<>();

        for(Movie movie : movies.values()){
            if(movie.getCategory().toLowerCase().contains(category.toLowerCase()))
                movieArrayList.add(movie);
        }

        movieArrayList.sort((m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle()));

        return movieArrayList;
    }

    //add movies to favourite
    public boolean addToFavourite(String userEmail, String movieTitle) {
        if(!isValidEmail(userEmail) || !users.containsKey(userEmail) || !movies.containsKey(movieTitle))
            return false;

        users.get(userEmail).addFavorite(movieTitle);

        return true;
    }

    //removing favorite movies
    public boolean removeFromFavorite(String userMail, String movieTitle) {
        if(!isValidEmail(userMail) || !users.containsKey(userMail))
            return false;

        users.get(userMail).removeFavorite(movieTitle);

        return true;
    }

    //watching the list of favourite movies
    public ArrayList<Movie> viewFavouriteMovies(String userMail) {
        if(!isValidEmail(userMail) || !users.containsKey(userMail))
            return null;

        HashSet<String> favouriteMovies = users.get(userMail).getFavorites();

        ArrayList<Movie> listOfFavourite = new ArrayList<>();
        for(String movie : favouriteMovies){
            listOfFavourite.add(movies.get(movie));
        }

        return listOfFavourite;
    }


    public ArrayList<Movie> searchOnlyFavouriteMovies(String userMail, String movieTitle) {
        if(!isValidEmail(userMail) || !users.containsKey(userMail))
            return null;

        ArrayList<Movie> favouriteMovies = viewFavouriteMovies(userMail);
        ArrayList<Movie> resultOfSearch = new ArrayList<>();
        if(favouriteMovies == null)
            return null;

        for(Movie movie : favouriteMovies){
            if(movie.getTitle().toLowerCase().contains(movieTitle.toLowerCase()))
                resultOfSearch.add(movie);
        }

        return resultOfSearch;
    }

    public User getUserDetails(String email) {
        if(!isValidEmail(email))
            return null;

        return users.get(email);
    }
}