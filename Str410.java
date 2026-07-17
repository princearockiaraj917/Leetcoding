// LeetCode 410: Split Array Largest Sum
// Strategy: Binary Search on the answer space with a greedy validation to check if the array can be split into at most k subarrays.
// Core Loop Condition: while(left <= right) with if(sum + n > mid) to create a new subarray during validation.
// Complexity: Time: O(n * log(sum)) | Space: O(1)

import java.util.Arrays;

class Str410 {

    public int splitArray(int[] nums, int k) {

        // The minimum possible answer is the largest element in the array.
        int left = nums[0];
        for (int n : nums) {
            left = Math.max(left, n);
        }

        // The maximum possible answer is the sum of all elements.
        int right = 0;
        int ans = 0;

        for (int n : nums) {
            right += n;
        }

        // Binary search on the possible maximum subarray sum.
        while (left <= right) {

            // Prevent integer overflow while finding the middle value.
            int mid = left + (right - left) / 2;

            // If this maximum sum is feasible, try to minimize it further.
            if (canSplit(mid, k, nums)) {
                ans = mid;
                right = mid - 1;
            }

            // Otherwise, increase the allowed maximum sum.
            else {
                left = mid + 1;
            }
        }

        return ans;
    }

    boolean canSplit(int mid, int k, int nums[]) {

        // Start with one subarray.
        int split = 1;
        int sum = 0;

        // Greedily build subarrays without exceeding the limit.
        for (int n : nums) {

            // If adding the current element exceeds the limit,
            // start a new subarray.
            if (sum + n > mid) {
                split++;
                sum = 0;
            }

            // Add the current element to the current subarray.
            sum += n;
        }

        // Return true only if the required number of subarrays
        // does not exceed k.
        return split <= k;
    }

    public static void main(String[] args) {

        Str410 obj = new Str410();

        // Test Case 1
        int[] nums1 = {7, 2, 5, 10, 8};
        System.out.println("Test 1");
        System.out.println("Array: " + Arrays.toString(nums1));
        System.out.println("k = 2");
        System.out.println("Answer = " + obj.splitArray(nums1, 2));
        System.out.println();

        // Test Case 2
        int[] nums2 = {1, 2, 3, 4, 5};
        System.out.println("Test 2");
        System.out.println("Array: " + Arrays.toString(nums2));
        System.out.println("k = 2");
        System.out.println("Answer = " + obj.splitArray(nums2, 2));
        System.out.println();

        // Test Case 3
        int[] nums3 = {1, 4, 4};
        System.out.println("Test 3");
        System.out.println("Array: " + Arrays.toString(nums3));
        System.out.println("k = 3");
        System.out.println("Answer = " + obj.splitArray(nums3, 3));
        System.out.println();

        // Test Case 4
        int[] nums4 = {2, 3, 1, 2, 4, 3};
        System.out.println("Test 4");
        System.out.println("Array: " + Arrays.toString(nums4));
        System.out.println("k = 5");
        System.out.println("Answer = " + obj.splitArray(nums4, 5));
        System.out.println();

        // Test Case 5
        int[] nums5 = {1, 1, 1, 1, 1};
        System.out.println("Test 5");
        System.out.println("Array: " + Arrays.toString(nums5));
        System.out.println("k = 5");
        System.out.println("Answer = " + obj.splitArray(nums5, 5));
    }
}