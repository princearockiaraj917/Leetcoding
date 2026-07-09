// LeetCode 1011: Capacity To Ship Packages Within D Days
// Strategy: Binary Search on the ship's capacity; simulate loading packages in order to check if all can be shipped within the given days.
// Core Loop Condition: while (min <= max) with if(check(mid, days, weights)) max = mid - 1; else min = mid + 1;
// Complexity: Time: O(n × log(sum(weights) - max(weights))) | Space: O(1)

class Str1011 {

    public int shipWithinDays(int[] weights, int days) {

        int min = Integer.MIN_VALUE;
        int max = 0;

        // Find the search range:
        // min = maximum package weight
        // max = total weight of all packages
        for (int x : weights) {
            max += x;
            if (x > min)
                min = x;
        }

        // Binary Search on the answer (ship capacity)
        while (min <= max) {

            int mid = min + (max - min) / 2; // Candidate ship capacity

            if (check(mid, days, weights)) {
                // Capacity works, try finding a smaller valid capacity
                max = mid - 1;
            } else {
                // Capacity is too small, increase it
                min = mid + 1;
            }
        }

        // 'min' points to the minimum valid ship capacity
        return min;
    }

    private boolean check(int mid, int days, int[] weights) {

        int day = 1;
        int sum = 0;

        // Simulate loading packages while maintaining their order
        for (int x : weights) {

            // Current package fits in today's shipment
            if (sum + x <= mid) {
                sum += x;
            } else {

                // Start shipping on the next day
                day++;

                // Exceeded the allowed number of days
                if (day > days)
                    return false;

                // Current package becomes the first package of the new day
                sum = x;
            }
        }

        // Successfully shipped within the given number of days
        return true;
    }

    public static void main(String[] args) {

        Str1011 obj = new Str1011();

        // Test Case 1 (LeetCode Example 1)
        int[] weights1 = {1,2,3,4,5,6,7,8,9,10};
        System.out.println("Test 1: " + obj.shipWithinDays(weights1, 5));
        // Expected: 15

        // Test Case 2 (LeetCode Example 2)
        int[] weights2 = {3,2,2,4,1,4};
        System.out.println("Test 2: " + obj.shipWithinDays(weights2, 3));
        // Expected: 6

        // Test Case 3 (LeetCode Example 3)
        int[] weights3 = {1,2,3,1,1};
        System.out.println("Test 3: " + obj.shipWithinDays(weights3, 4));
        // Expected: 3

        // Test Case 4 (Ship everything in one day)
        int[] weights4 = {5,4,3,2,1};
        System.out.println("Test 4: " + obj.shipWithinDays(weights4, 1));
        // Expected: 15

        // Test Case 5 (One package shipped each day)
        int[] weights5 = {5,4,3,2,1};
        System.out.println("Test 5: " + obj.shipWithinDays(weights5, 5));
        // Expected: 5
    }
}