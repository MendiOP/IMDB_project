package org.example.handler;

import org.example.lister.MovieListing;
import org.example.movies.Movie;
import org.example.user.User;

import java.util.ArrayList;
import java.util.Scanner;


public class Handler {

    private static void userMenu(Scanner scanner, String email, MovieListing movieListing){

        while (true){
            System.out.println("\n1. Search Movies");
            System.out.println("2. Add to Favorites");
            System.out.println("3. View Favorites");
            System.out.println("4. Search Favorites");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 1){
                System.out.print("1. Search by title       2. Search by Cast       3. Search by Category\n");
                int input = scanner.nextInt();
                scanner.nextLine();

                while(input > 3 || input < 1){
                    System.out.println("\nInvalid Option. Choose another");
                    System.out.print("1. Search by title       2. Search by Cast       3. Search by Category\n");
                    input = scanner.nextInt();
                    scanner.nextLine();
                }

                ArrayList<Movie> movies = null;

                if(input == 1){
                    System.out.print("\nProvide title of the movie : ");
                    String title = scanner.nextLine();
                    movies = movieListing.searchByTitle(title);
                }else if(input == 2){
                    System.out.print("\nProvide Cast name : ");
                    String cast = scanner.nextLine();
                    movies = movieListing.searchByCast(cast);
                }else {
                    System.out.print("\nProvide Category name : ");
                    String category = scanner.nextLine();
                    movies = movieListing.searchByCategory(category);
                }

                if(movies.isEmpty())
                    System.out.println("Sorry, there is no movie with your choice");
                else {
                    for (Movie movie : movies)
                        System.out.println(movie.toString());
                }

            }else if(choice == 2){
                System.out.print("\nEnter movie title to add to favorites: ");
                String title = scanner.nextLine();

                if(movieListing.addToFavourite(email, title))
                    System.out.println("Added to the favourites successfully.");
                else
                    System.out.println("Movie was not found. Put a movie to favourite list from existing movie database");

            }else if(choice == 3){
                ArrayList<Movie> list = movieListing.viewFavouriteMovies(email);

                if(list.isEmpty())
                    System.out.println("Oopss.. No favorite movies till now.");
                else {
                    System.out.println("Your Favourite Movies are : ");
                    for (Movie movie : list)
                        System.out.println(movie.toString());
                }

            }else if(choice == 4){
                System.out.print("Give the movie name to search in your favourites : ");
                String movie = scanner.nextLine();

                ArrayList<Movie> list = movieListing.searchOnlyFavouriteMovies(email, movie);
                if(list.isEmpty()){
                    System.out.println("Oopss... No movies found");
                }else {
                    for (Movie movie1 : list)
                        System.out.println(movie1.toString());
                }

            }
            else if(choice == 5){
                System.out.println("Logging out...");
                break;
            }
            else{
                System.out.println("Invalid Option1");
            }
        }
    }
    public static void main(String[] args) {
        MovieListing movieListing = new MovieListing();
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("<------- Please Select any of them ------->");
            System.out.println("1. Log in");
            System.out.println("2. Register");
            System.out.println("3. Exit.");

            System.out.print("Make your choice : ");
            int input = scanner.nextInt();
            scanner.nextLine();

            if(input >= 3)
                break;
            else if(input == 2){
                System.out.print("Provide your email address : ");
                String email =  scanner.nextLine();

                if(movieListing.registerUser(email))
                    System.out.println("Registration Successfully Done!");
                else
                    System.out.println("Something went wrong. User may be already registered or try to provide a valid email");
            }
            else{
                System.out.print("Provide your email address : ");
                String email =  scanner.nextLine();

                User user = movieListing.getUserDetails(email);
                if(user == null)
                    System.out.println("Oops.. Email was to found");
                else {
                    System.out.println("Login Successful");
                    userMenu(scanner, email, movieListing);
                }
            }
        }
    }
}
