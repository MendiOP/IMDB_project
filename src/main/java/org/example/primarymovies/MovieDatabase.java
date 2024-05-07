package org.example.primarymovies;

import org.example.lister.MovieListing;

public class MovieDatabase {
    public static void initialize(MovieListing movieListing){
        movieListing.addMovies("Beder Meye Josna", "Iliyas Kanchon", "Romantic-Drama", "2-3-1993", "3cr");
        movieListing.addMovies("kacher manush dure thuiya", "Farin-Pritom", "Romantic-Realization", "1-1-2024", "50Lacs");
        movieListing.addMovies("Dhamal", "Sanjay Datt", "Comedy", "7-9-2007", "1cr");
        movieListing.addMovies("Monpura", "Chanchal Chowd.", "Romantic-Realization", "13-2-2009", "5Lacs");
        movieListing.addMovies("Green Card", "Maruf Hayat", "Time Waste", "15-4-2024", "4.5cr");
        movieListing.addMovies("Avengers:End Game", "RDJ-Chris Evans", "Scifi Action", "26-4-19", "3908cr");
        movieListing.addMovies("Rajkumar", "Shakib Khan", "Romantic-Comedy", "16-5-2024", "50Lacs");
    }
}
