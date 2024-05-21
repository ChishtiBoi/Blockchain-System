package org.example;

// Class representing proof of difficulty for blockchain mining
public class ProofOfDifficulty {

    private HashFunction hashFunction; // Instance of HashFunction to calculate hash

    // Constructor to initialize ProofOfDifficulty with a HashFunction instance
    public ProofOfDifficulty(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
    }

    // Method to generate proof of work
    public int generateProof(int difficulty, String previousProof) {
        int proof = 0; // Initialize proof to 0
        // Iterate until a valid proof of work is found
        while (!isValidProof(difficulty, previousProof, proof)) {
            proof++; // Increment proof
        }
        return proof; // Return the valid proof of work
    }

    // Method to check if proof of work is valid
    public boolean isValidProof(int difficulty, String previousProof, int proof) {
        String guess = previousProof + Integer.toString(proof); // Concatenate previous proof with current proof
        String guessHash = hashFunction.calculateHash(guess); // Calculate hash of the guess
        // Check if the hash meets the difficulty requirement (starts with '0' repeated 'difficulty' times)
        return guessHash.startsWith("0".repeat(difficulty));
    }
}
