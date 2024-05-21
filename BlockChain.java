package org.example;

import java.util.ArrayList;
import org.example.BinarySearch;

// Class representing a blockchain
public class BlockChain {

    // Instance variables
    public ArrayList<Block> blockChain;           // List to hold blocks in the blockchain
    private ProofOfDifficulty proofOfDifficulty;   // Proof of Difficulty instance for mining blocks

    // Constructor to initialize the blockchain with a genesis block
    public BlockChain(ProofOfDifficulty proofOfDifficulty) {
        blockChain = new ArrayList<>();            // Initialize the blockchain as an ArrayList
        this.proofOfDifficulty = proofOfDifficulty; // Assign the proof of difficulty instance
        createGensisBlock();                        // Create the genesis block when a new blockchain is instantiated
    }

    // Method to create the genesis block and add it to the blockchain
    private void createGensisBlock() {
        blockChain.add(new Block(1, "Genesis Block created", "0")); // Adding the genesis block with index 1 and previous hash "0"
    }

    // Method to add a new block to the blockchain
    public void addNewBlock(String data, User user) {
        Block latestBlock = blockChain.get(blockChain.size() - 1); // Get the latest block in the blockchain
        int newIndex = latestBlock.getIndex() + 1;                 // Calculate the index for the new block
        String previousHash = latestBlock.getHash();               // Get the hash of the latest block
        // Generate proof of work for the new block
        int proofOfWork = proofOfDifficulty.generateProof(4, Integer.toString(latestBlock.getProofOfWork()));
        // Create the new block with the calculated attributes
        Block newBlock = new Block(newIndex, data, previousHash, user);
        newBlock.setProofOfWork(proofOfWork);                      // Set the proof of work for the new block
        blockChain.add(newBlock);                                  // Add the new block to the blockchain
    }

    // Method to retrieve the entire blockchain
    public ArrayList<Block> getBlockChain() {
        return blockChain;
    }

    // Method to validate the integrity of the blockchain
    public boolean validateChain() {
        Validation validation = new Validation();          // Create a validation object
        return validation.validateBlockchain(blockChain, proofOfDifficulty); // Validate the blockchain
    }

    // Method to sort the blockchain by index
    public void sortBlockchainByIndex() {
        SelectionSort.sortByIndex(blockChain);            // Sort the blockchain by index
    }

    // Method to search for a block by index using binary search
    public Block searchBlockByIndex(int targetIndex) {
        return BinarySearch.searchByIndex(blockChain, targetIndex); // Perform binary search on the blockchain
    }

}
