package org.example.user;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String email;
    private Set<String> favorites;

    public User(String email) {
        this.email = email;
        this.favorites = new HashSet<>();
    }

    public String getEmail() {
        return email;
    }

    public Set<String> getFavorites() {
        return favorites;
    }

    public void addFavorite(String title) {
        favorites.add(title);
    }

    public void removeFavorite(String title) {
        favorites.remove(title);
    }
}
