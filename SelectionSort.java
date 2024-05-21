package org.example;

import java.util.ArrayList;

// Class representing selection sort algorithm for sorting a blockchain
public class SelectionSort {

    // Method to perform selection sort on the blockchain by index
    public static void sortByIndex(ArrayList<Block> blockchain) {
        int n = blockchain.size(); // Get the size of the blockchain
        int i = 0; // Initialize loop counter

        // Outer loop to traverse through the blockchain
        while (i < n - 1) {
            int minIndex = i; // Initialize the index of the minimum element to the current index
            int j = i + 1; // Start comparing from the next element

            // Inner loop to find the index of the minimum element
            while (j < n) {
                // Compare indices of blocks to find the minimum index
                if (blockchain.get(j).getIndex() < blockchain.get(minIndex).getIndex()) {
                    minIndex = j; // Update the index of the minimum element
                }
                j++; // Move to the next element
            }

            // Swap blocks at the current index and the minimum index
            Block temp = blockchain.get(minIndex);
            blockchain.set(minIndex, blockchain.get(i));
            blockchain.set(i, temp);

            i++; // Move to the next element for the next iteration
        }
    }
}
