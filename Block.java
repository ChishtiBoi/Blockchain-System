package org.example;


import java.util.Date;

// Class representing a block in a blockchain
public class Block {

    // Instance variables
    private int index;           // Index of the block in the blockchain
    private Date timestamp;      // Timestamp of when the block was created
    private String data;         // Data stored in the block
    private String hash;         // Hash of the block
    private String previousHash; // Hash of the previous block in the blockchain
    private int proofOfWork;     // Proof of work for the block
    private User user;           // User associated with the block

    // Constructor for creating a block without a user
    public Block(int index, String data, String previousHash) {
        this.index = index;
        this.timestamp = new Date(); // Set current timestamp
        this.data = data;
        this.previousHash = previousHash;
        this.proofOfWork = 0; // Initialize proof of work to 0
        this.hash = calculateHash(); // Calculate hash of the block
    }

    // Constructor for creating a block with a user
    public Block(int index, String data, String previousHash, User user) {
        this.index = index;
        this.timestamp = new Date(); // Set current timestamp
        this.data = data;
        this.previousHash = previousHash;
        this.proofOfWork = 0; // Initialize proof of work to 0
        this.user = user;
        this.hash = calculateHash(); // Calculate hash of the block
    }

    // Method to calculate hash for the block
    public String calculateHash() {
        // Concatenate block attributes to form input for hash calculation
        String input = index + data + timestamp + previousHash;
        // If a user is associated with the block, include user credentials in the input
        if (user != null) {
            input += user.hashCredentials();
        }
        // Create a HashFunction object and calculate hash of the input
        HashFunction hashFunction = new HashFunction();
        return hashFunction.calculateHash(input);
    }

    // Getter and setter methods for instance variables
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public int getProofOfWork() {
        return proofOfWork;
    }

    public void setProofOfWork(int proofOfWork) {
        this.proofOfWork = proofOfWork;
    }

}
