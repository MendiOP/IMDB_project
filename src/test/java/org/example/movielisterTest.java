package org.example;

import org.example.lister.MovieListing;
import org.example.movies.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class movielisterTest {

    @Test
    public void testEmailValidator(){
        MovieListing movieListing = new MovieListing();

        assertFalse(movieListing.isValidEmail("@344@mail.com"));
        assertTrue(movieListing.isValidEmail("hfasjfja@mail.com"));
        assertFalse(movieListing.isValidEmail("sdasd sdsad"));
        assertFalse(movieListing.isValidEmail(".com"));
    }

    @Test
    public void testUserRegistration(){
        MovieListing movieListing = new MovieListing();

        assertTrue(movieListing.registerUser("hfasjfja@mail.com"));
        assertTrue(movieListing.registerUser("hfsdasjfja@mail.com"));
        assertFalse(movieListing.registerUser("hfasjfja@mail.com"));
    }

    @Test
    public void testAddMovies(){
        MovieListing movieListing = new MovieListing();

        //title, cast, category, release date, budget.
        movieListing.addMovies("Beder Meye Josna", "Iliyas Kanchon", "Romantic-Drama", "2-3-1993", "3cr");
       // movieListing.addMovies("kacher manush dure thuiya", "Farin-Pritom", "Romantic-Realization", "1-1-2024", "50Lacs");

        assertNotNull(movieListing.getMovieDetails("Beder Meye Josna"));
    }

    @Test
    public void testSearchMovieByTitle(){
        MovieListing movieListing =  new MovieListing();

        assertNotNull(movieListing.searchByTitle("Beder Meye Josna"));
        assertNotNull(movieListing.searchByTitle("Dhamal"));

    }

    @Test
    public void testSearchByCast(){
        MovieListing movieListing = new MovieListing();

        ArrayList<Movie> movies = movieListing.searchByCast("Maruf Hayat");
        String[] title = new String[movies.size()];
        int i=0;
        for(Movie movie : movies)
            title[i++] = movie.getTitle();

        assertArrayEquals(new String[]{"Green Card", "Omanush"}, title);
    }

    @Test
    public void testSearchByCategory(){
        MovieListing movieListing = new MovieListing();

        ArrayList<Movie> movies = movieListing.searchByCategory("Romantic");
        String[] title = new String[movies.size()];
        int i=0;
        for(Movie movie : movies)
            title[i++] = movie.getTitle();

        assertArrayEquals(new String[]{"Beder Meye Josna", "kacher manush dure thuiya", "Monpura", "Rajkumar"}, title);

    }

    @Test
    public void testAddToFavourite(){
        MovieListing movieListing = new MovieListing();
        assertFalse(movieListing.isValidEmail("@test@gmail.com"));
        assertFalse(movieListing.addToFavourite("@test@gmail.com", "Rajkumar"));
    }

    @Test
    public void testRemoveFromFavourite(){
        MovieListing movieListing = new MovieListing();

        movieListing.registerUser("test@gmail.com");
        movieListing.addMovies("Fana", "Amir", "Drama", "1-1-10", "2cr");
        movieListing.addMovies("ABCD", "Jackson", "Drama", "1-1-10", "2cr");
        movieListing.addMovies("Dhaka Attack", "Shuvo", "Drama", "1-1-10", "2cr");

        movieListing.addToFavourite("test@gmail.com", "ABCD");
        movieListing.addToFavourite("test@gmail.com", "Fana");
        movieListing.addToFavourite("sfaf.com", "sdfaf");

        assertTrue(movieListing.removeFromFavorite("test@gmail.com", "ABCD"));
        assertFalse(movieListing.removeFromFavorite("sfaf.com", "sdfaf"));
    }

    @Test
    public void testViewFavouriteMovies(){
        MovieListing movieListing = new MovieListing();

        movieListing.registerUser("test@gmail.com");
        movieListing.addMovies("Fana", "Amir", "Drama", "1-1-10", "2cr");
        movieListing.addMovies("ABCD", "Jackson", "Drama", "1-1-10", "2cr");
        movieListing.addMovies("Dhaka Attack", "Shuvo", "Drama", "1-1-10", "2cr");

        movieListing.addToFavourite("test@gmail.com", "ABCD");
        movieListing.addToFavourite("test@gmail.com", "Fana");
        movieListing.addToFavourite("sfaf.com", "sdfaf");

        ArrayList<Movie> movies = movieListing.viewFavouriteMovies("test@gmail.com");
        String[] name = new String[movies.size()];
        int i=0;
        for(Movie movie : movies)
            name[i++] = movie.getTitle();

        assertArrayEquals(new String[]{"ABCD", "Fana"}, name);
    }

    @Test
    public void testSearchOnlyFavouriteMovies(){
        MovieListing movieListing = new MovieListing();

        movieListing.registerUser("test@gmail.com");

        movieListing.addMovies("ABCD", "Jackson", "Drama", "1-1-10", "2cr");
        movieListing.addMovies("Dhaka Attack", "Shuvo", "Drama", "1-1-10", "2cr");
        movieListing.addMovies("Roktim Full", "Jackson", "Drama", "1-1-10", "2cr");


        movieListing.addToFavourite("test@gmail.com", "ABCD");
        movieListing.addToFavourite("test@gmail.com", "Roktim Full");

        ArrayList<Movie> listOfFavourite =  movieListing.searchOnlyFavouriteMovies("test@gmail.com", "Roktim");
        String[] name = new String[listOfFavourite.size()];
        int i=0;
        for(Movie movie : listOfFavourite)
            name[i++] = movie.getTitle();

        assertArrayEquals(new String[]{"Roktim Full"}, name);

    }

    @Test void testGetPersonalDetails(){
        MovieListing app = new MovieListing();
        app.registerUser("test@example.com");
        assertNotNull(app.getUserDetails("test@example.com"));
    }
}
