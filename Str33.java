// LeetCode 33: Search in Rotated Sorted Array
// Strategy: Modified Binary Search by identifying the sorted half each iteration and checking whether the target lies within that half.
// Core Loop Condition: while (left <= right)
// Complexity: Time: O(log n) because half of the search space is discarded each iteration | Space: O(1) because only constant extra variables are used

public class Str33 {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // Continue narrowing the search space until all possibilities are exhausted.
        while (left <= right) {
            int mid = (left + right) / 2;

            // Return immediately since the target has been found.
            if (nums[mid] == target)
                return mid;

            // Determine whether the left half is the sorted portion.
            if (nums[left] <= nums[mid]) {

                // Restrict the search to the sorted left half only if the target lies within its range.
                if (nums[left] <= target && nums[mid] > target) {
                    right = mid - 1;
                } else {

                    // Discard the sorted half because the target cannot exist within its boundaries.
                    left = mid + 1;
                }
            } else {

                // The right half is sorted; search it only if the target falls within its range.
                if (nums[mid] < target && nums[right] >= target) {
                    left = mid + 1;
                } else {

                    // Eliminate the sorted right half since the target must lie on the opposite side.
                    right = mid - 1;
                }
            }
        }

        // The target is not present in the array.
        return -1;
    }

    public static void main(String[] args) {
        Str33 obj = new Str33();

        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(obj.search(nums1, 0));   // Expected: 4

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(obj.search(nums2, 3));   // Expected: -1

        int[] nums3 = {1};
        System.out.println(obj.search(nums3, 0));   // Expected: -1

        int[] nums4 = {1, 3};
        System.out.println(obj.search(nums4, 3));   // Expected: 1

        int[] nums5 = {5, 1, 3};
        System.out.println(obj.search(nums5, 5));   // Expected: 0
    }
}