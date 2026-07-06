// LeetCode 875: Koko Eating Bananas
// Strategy: Binary Search on the eating speed, validating each candidate by summing the hours required to finish every pile using ceiling division.
// Core Loop Condition: while (min < max)
// Complexity: Time: O(n log M) because each binary search step scans all piles | Space: O(1) because only constant extra variables are used.



class Str875 {
    public int minEatingSpeed(int[] piles, int h) {
        // The slowest possible speed is 1 banana per hour.
        int min = 1;

        int max = 0;

        // The largest pile is the highest speed worth considering.
        for (int n : piles) {
            max = Math.max(max, n);
        }

        // Narrow the search until the minimum feasible eating speed is found.
        while (min < max) {
            int mid = (min + max) / 2;

            // If this speed finishes on time, search for an even smaller valid speed.
            if (hours(piles, mid, h)) {
                max = mid;
            }
            // Otherwise, increase the speed to satisfy the time limit.
            else {
                min = mid + 1;
            }
        }

        // Both pointers converge to the minimum valid eating speed.
        return min;
    }

    private boolean hours(int[] piles, int mid, int h) {
        int hr = 0;

        for (int x : piles) {
            // Ceiling division counts the full hour needed even for partially eaten piles.
            hr += (x + mid - 1) / mid;
        }

        // A speed is valid only if all piles can be finished within the allowed hours.
        return hr <= h;
    }

    public static void main(String[] args) {
        Str875 solution = new Str875();

        // Test Case 1: Example 1
        int[] piles1 = {3, 6, 7, 11};
        System.out.println("Test Case 1: " + solution.minEatingSpeed(piles1, 8));
        // Expected Output: 4

        // Test Case 2: Example 2
        int[] piles2 = {30, 11, 23, 4, 20};
        System.out.println("Test Case 2: " + solution.minEatingSpeed(piles2, 5));
        // Expected Output: 30

        // Test Case 3: Example 3
        int[] piles3 = {30, 11, 23, 4, 20};
        System.out.println("Test Case 3: " + solution.minEatingSpeed(piles3, 6));
        // Expected Output: 23

        // Test Case 4: Single pile
        int[] piles4 = {100};
        System.out.println("Test Case 4: " + solution.minEatingSpeed(piles4, 10));
        // Expected Output: 10

        // Test Case 5: Large pile sizes with extra hours
        int[] piles5 = {312884470, 312884469, 312884468};
        System.out.println("Test Case 5: " + solution.minEatingSpeed(piles5, 1000000000));
        // Expected Output: 1
    }
}