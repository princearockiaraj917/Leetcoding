// LeetCode 153: Find Minimum in Rotated Sorted Array
// Strategy: Use Binary Search to repeatedly discard the sorted half and continue searching in the half containing the rotation point until the remaining search space becomes fully sorted.
// Core Loop Condition: while (left <= right)
// Complexity: Time: O(log n) because half of the search space is eliminated in each iteration | Space: O(1) because only constant extra variables are used


/*
1. Check if the current search space is already sorted.
      YES → nums[left] is the minimum → Return it.

2. Otherwise, one half is sorted and one half contains the rotation.

3. Ignore the sorted half because the minimum cannot be there.

4. Continue searching only in the unsorted half.

5. Eventually the remaining search space itself becomes sorted.

6. The first element of that sorted search space is the minimum.
*/


public class Str153 {

    public int findMin(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        // Keep narrowing the search space until the minimum is isolated.
        while (left <= right) {

            // If the current range is already sorted, the leftmost element is the minimum.
            if (nums[left] <= nums[right])
                return nums[left];

            int mid = left + (right - left) / 2;

            // A sorted left half cannot contain the rotation point, so discard it.
            if (nums[left] <= nums[mid]) {
                left = mid + 1;
            } else {

                // Keep the unsorted left half because it contains the rotation point and possibly the minimum.
                right = mid;
            }
        }

        // The search space has converged to the minimum element.
        return nums[left];
    }

    public static void main(String[] args) {

        Str153 obj = new Str153();

        // Standard rotated array with the minimum in the middle.
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println(obj.findMin(nums1));   // Expected: 1

        // Rotation occurs after several elements.
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(obj.findMin(nums2));   // Expected: 0

        // Already sorted array with no rotation.
        int[] nums3 = {11, 13, 15, 17};
        System.out.println(obj.findMin(nums3));   // Expected: 11

        // Smallest possible rotated array.
        int[] nums4 = {2, 1};
        System.out.println(obj.findMin(nums4));   // Expected: 1

        // Rotation occurs immediately after the first element.
        int[] nums5 = {5, 1, 2, 3, 4};
        System.out.println(obj.findMin(nums5));   // Expected: 1
    }
}