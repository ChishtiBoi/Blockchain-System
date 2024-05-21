package org.example;

import java.util.ArrayList;


public class BinarySearch {

    // Method to perform binary search on the blockchain by index
    public static Block searchByIndex(ArrayList<Block> blockchain, int targetIndex) {
        // Initialize the leftmost and rightmost indices of the search range
        int left = 0;
        int right = blockchain.size() - 1;

        // Loop until the search range is valid
        for (; left <= right; ) {
            // Calculate the middle index of the current search range
            int mid = left + (right - left) / 2;
            // Retrieve the index of the block at the middle index
            int midIndex = blockchain.get(mid).getIndex();

            // Check if the middle block's index matches the target index
            if (midIndex == targetIndex) {
                // Return the block found at the target index
                return blockchain.get(mid);
            }

            // If the middle block's index is less than the target index,
            // adjust the left boundary of the search range
            if (midIndex < targetIndex) {
                left = mid + 1;
            } else {
                // If the middle block's index is greater than the target index,
                // adjust the right boundary of the search range
                right = mid - 1;
            }
        }

        // If the loop exits without finding the target index, return null
        return null; // Block not found
    }
}
