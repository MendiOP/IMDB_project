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
    public void testUserReistration(){
        MovieListing movieListing = new MovieListing();
        assertTrue(movieListing.registerUser("hfasjfja@mail.com"));
        assertTrue(movieListing.registerUser("hfsdasjfja@mail.com"));
        assertFalse(movieListing.registerUser("hfasjfja@mail.com"));
    }
}
