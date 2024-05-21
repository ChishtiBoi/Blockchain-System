package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Class representing a hash function for generating SHA-256 hashes
public class HashFunction {

    // Method to calculate the SHA-256 hash of the input string
    public String calculateHash(String input) {
        try {
            // Create a MessageDigest object using SHA-256 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Generate the hash bytes for the input string
            byte[] hash = digest.digest(input.getBytes());

            // Convert the hash bytes to hexadecimal representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                // Convert each byte to its hexadecimal representation
                String hex = Integer.toHexString(0xff & b);
                // If the hexadecimal string is a single digit, prepend '0' to make it two digits
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            // Return the hexadecimal representation of the hash
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the case where the specified algorithm (SHA-256) is not available
            e.printStackTrace();
            return null;
        }
    }
}
