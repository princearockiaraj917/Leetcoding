// LeetCode [Problem Number]: Mirror Distance
// Strategy: Reverse the digits of the number and return the absolute difference between the original and reversed values.
// Core Loop Condition: while (x > 0)
// Complexity: Time: O(d) - processes each digit once | Space: O(1) - uses only constant extra variables

class math3783.java {

    public int mirrorDistance(int n) {
        int x = n;
        int rev = 0;

        // Reverse the digits to obtain the mirror number.
        while (x > 0) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }

        // Return the absolute difference irrespective of reversal order.
        return Math.abs(n - rev);
    }

    public static void main(String[] args) {
        Solution obj = new Solution();

        System.out.println(obj.mirrorDistance(123));    // 198
        System.out.println(obj.mirrorDistance(100));    // 99
        System.out.println(obj.mirrorDistance(909));    // 0
        System.out.println(obj.mirrorDistance(12045));  // 42066
        System.out.println(obj.mirrorDistance(7));      // 0
    }
}