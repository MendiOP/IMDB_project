package org.example;

import org.example.lister.MovieListing;
import org.junit.jupiter.api.Test;
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
}
