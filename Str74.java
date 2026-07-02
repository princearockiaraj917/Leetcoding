/*
------------------------------------------------------------------------------
Problem : 74. Search a 2D Matrix
Difficulty : Medium

Approach:
Treat the 2D matrix as a single sorted 1D array and apply Binary Search.
Convert the 1D index back into (row, column) using:
    row = mid / numberOfColumns
    col = mid % numberOfColumns

Time Complexity:
O(log(m × n))

Space Complexity:
O(1)
------------------------------------------------------------------------------
*/

class Solution {

    public boolean searchMatrix(int[][] matrix, int target)
    {
        // Total number of rows
        int m = matrix.length;

        // Total number of columns
        int n = matrix[0].length;

        // Binary Search over the imaginary 1D array
        int left = 0;
        int right = (m * n) - 1;

        while (left <= right)
        {
            // Find middle index
            int mid = left + (right - left) / 2;

            // Convert 1D index into 2D coordinates
            int row = mid / n;
            int col = mid % n;

            // Target found
            if (matrix[row][col] == target)
            {
                return true;
            }

            // Search left half
            else if (matrix[row][col] > target)
            {
                right = mid - 1;
            }

            // Search right half
            else
            {
                left = mid + 1;
            }
        }

        // Target not present
        return false;
    }
}

public class Str74 {

    public static void main(String[] args)
    {
        Solution obj = new Solution();

        // Test Case 1 - Target Present
        int[][] matrix1 = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        System.out.println("Test Case 1");
        System.out.println("Target : 3");
        System.out.println("Output : " + obj.searchMatrix(matrix1, 3));
        // Expected : true

        System.out.println();

        // Test Case 2 - Target Absent
        System.out.println("Test Case 2");
        System.out.println("Target : 13");
        System.out.println("Output : " + obj.searchMatrix(matrix1, 13));
        // Expected : false

        System.out.println();

        // Test Case 3 - Single Element Matrix (Present)
        int[][] matrix2 = {
                {5}
        };
        System.out.println("Test Case 3");
        System.out.println("Target : 5");
        System.out.println("Output : " + obj.searchMatrix(matrix2, 5));
        // Expected : true

        System.out.println();

        // Test Case 4 - Single Element Matrix (Absent)
        System.out.println("Test Case 4");
        System.out.println("Target : 2");
        System.out.println("Output : " + obj.searchMatrix(matrix2, 2));
        // Expected : false

        System.out.println();

        // Test Case 5 - Last Element (Edge Case)
        System.out.println("Test Case 5");
        System.out.println("Target : 60");
        System.out.println("Output : " + obj.searchMatrix(matrix1, 60));
        // Expected : true
    }
}