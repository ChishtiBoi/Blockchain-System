package org.example;

import java.util.ArrayList;

// Class for validating the integrity of the blockchain
public class Validation {

    // Method to validate the integrity of the blockchain
    public static boolean validateBlockchain(ArrayList<Block> blockchain, ProofOfDifficulty proofOfDifficulty) {
        for (int i = 1; i < blockchain.size(); i++) {
            Block currentBlock = blockchain.get(i); // Get the current block
            Block previousBlock = blockchain.get(i - 1); // Get the previous block

            // Check if the previousHash matches the hash of the previous block
            // and if the current block's hash matches the result of calculateHash()
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash()) ||
                    !currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false; // Return false if integrity is compromised
            }

            // Check if the proof of work is valid
            if (!proofOfDifficulty.isValidProof(4, Integer.toString(previousBlock.getProofOfWork()), currentBlock.getProofOfWork())) {
                return false; // Return false if proof of work is invalid
            }
        }
        return true; // Return true if blockchain integrity is maintained
    }

}
