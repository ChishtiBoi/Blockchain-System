package org.example;

// Class representing a user in the blockchain system
public class User {
    private String username; // Username of the user
    private String password; // Password of the user

    // Constructor to initialize username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter method for username
    public String getUsername() {
        return username;
    }

    // Setter method for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter method for password
    public String getPassword() {
        return password;
    }

    // Setter method for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Method to hash the username and password
    public String hashCredentials() {
        String input = username + password; // Concatenate username and password
        HashFunction hashFunction = new HashFunction(); // Create a HashFunction object
        return hashFunction.calculateHash(input); // Calculate hash of the concatenated string
    }
}
