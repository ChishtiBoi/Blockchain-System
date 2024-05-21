package org.example;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;

import static spark.Spark.*;

// Main class to set up and run the blockchain application
public class Main {
    private static BlockChain blockchain;

    // Main method to start the application
    public static void main(String[] args) {
        // Initialize blockchain with proof of difficulty
        ProofOfDifficulty proofOfDifficulty = new ProofOfDifficulty(new HashFunction());
        blockchain = new BlockChain(proofOfDifficulty);

        // Set port for Spark web server
        port(8080);

        // Define endpoints for handling HTTP requests
        post("/addBlock", Main::handleAddBlock);     // Add a new block
        get("/chain", Main::handleGetChain);         // Get the entire blockchain
        get("/validate", Main::handleValidateChain); // Validate the blockchain
        get("/sort", Main::handleSortChain);         // Sort the blockchain by index
        get("/search", Main::handleSearchBlockByIndex); // Search for a block by index
    }

    // Handler method to add a new block to the blockchain
    private static String handleAddBlock(Request req, Response res) {
        String data = req.queryParams("data");     // Get block data from query parameters
        String username = req.queryParams("username"); // Get username from query parameters
        String password = req.queryParams("password"); // Get password from query parameters

        // Validate inputs
        if (data == null || username == null || password == null) {
            res.status(400); // Bad request
            return "Please provide data, username, and password.";
        }

        User user = new User(username, password); // Create a User object

        // Add the block with user information
        blockchain.addNewBlock(data, user);
        return "Block added successfully!";
    }

    // Handler method to get the entire blockchain
    private static String handleGetChain(Request req, Response res) {
        ArrayList<Block> chain = blockchain.getBlockChain();
        return new Gson().toJson(chain); // Convert blockchain to JSON format
    }

    // Handler method to validate the blockchain
    private static String handleValidateChain(Request req, Response res) {
        boolean isValid = blockchain.validateChain();
        ProofOfDifficulty proofOfDifficulty = new ProofOfDifficulty(new HashFunction());
        boolean isValidWithProof = Validation.validateBlockchain(blockchain.getBlockChain(), proofOfDifficulty);
        return "Blockchain is valid without proof of work: " + isValid + "\nBlockchain is valid with proof of work: " + isValidWithProof;
    }

    // Handler method to sort the blockchain by index
    private static String handleSortChain(Request req, Response res) {
        blockchain.sortBlockchainByIndex(); // Sort the blockchain by index
        ArrayList<Block> sortedChain = blockchain.getBlockChain(); // Get the sorted blockchain
        return new Gson().toJson(sortedChain); // Convert sorted blockchain to JSON format
    }

    // Handler method to search for a block by index
    private static String handleSearchBlockByIndex(Request req, Response res) {
        try {
            int targetIndex = Integer.parseInt(req.queryParams("index")); // Get target index from query parameters
            Block block = blockchain.searchBlockByIndex(targetIndex); // Search for block by index
            if (block != null) {
                return new Gson().toJson(block); // Convert block to JSON format
            } else {
                res.status(404); // Not found
                return "Block not found for the provided index.";
            }
        } catch (NumberFormatException e) {
            res.status(400); // Bad request
            return "Invalid index format.";
        }

    }

}

