// LeetCode 66: Plus One
// Strategy: Convert the digit array into a number, add 1, then convert it back into the array.
// Core Loop Condition: for(int i = digits.length - 1; i >= 0; i--) and while(num != 0 && j >= 0)
// Complexity: Time: O(n) | Space: O(1)

import java.util.Arrays;

class Str66 {

    public int[] plusOne(int[] digits) {

        // Stores the integer formed from the digit array.
        int n = 0;

        // Starts filling the answer from the last index.
        int j = digits.length - 1;

        // Convert the digit array into an integer.
        for (int i = digits.length - 1; i >= 0; i--) {
            n = (n * 10) + digits[i];
        }

        // Add one to the formed number.
        n = n + 1;

        // Copy the incremented value into another variable for extraction.
        int num = n;

        // Extract digits from right to left and place them back into the array.
        while (num != 0 && j >= 0) {
            digits[j] = num % 10;
            num = num / 10;
            j--;
        }

        // Return the updated digit array.
        return digits;
    }

    public static void main(String[] args) {

        // Create an object of the class.
        Str66 obj = new Str66();

        // Test cases.
        int[][] tests = {
            {1, 2, 3},
            {4, 3, 2, 1},
            {9},
            {1, 9, 9},
            {9, 9, 9}
        };

        // Execute all test cases.
        for (int i = 0; i < tests.length; i++) {

            System.out.println("Test Case " + (i + 1));

            // Display input.
            System.out.println("Input : " + Arrays.toString(tests[i]));

            // Call the method and display output.
            System.out.println("Output: " + Arrays.toString(obj.plusOne(tests[i])));

            System.out.println();
        }
    }
}