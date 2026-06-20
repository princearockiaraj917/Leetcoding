import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// LeetCode 15: 3Sum
// Strategy: Sort the array first, then use a primary anchor element loop coupled with a Two-Pointer inward scan to capture zero-sum complements.
// Core Loop Condition: for(int i=0;i<nums.length-2;i++) inside outer loop; while(start<end) inside inner loop
// Complexity: Time: O(n^2) dominated by internal two-pointer scanning loops across nested target values | Space: O(1) excluding final storage allocations.
class Str15 {
    public List<List<Integer>> threeSum(int[] nums) 
    {
        List<List<Integer>> result = new ArrayList<>();
        
        // Group similar numbers together to cleanly structure subsequent pointer movements
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++)
        {
            // Skip processing if the current static anchor matches the preceding element to prevent identical output triplets
            if(i != 0 && nums[i] == nums[i - 1]) continue;
            int n1 = nums[i];
            int start = i + 1;
            int end = nums.length - 1;
            
            while(start < end)
            {
                int n2 = nums[start];
                int n3 = nums[end];
                int sum = n1 + n2 + n3;
                if(sum == 0)
                {
                    List<Integer> list = new ArrayList<>();
                    list.add(n1);
                    list.add(n2);
                    list.add(n3);
                    result.add(list);
                    
                    // Discard matching adjacent values forward to eliminate identical combinations
                    while(start < nums.length - 1 && nums[start] == nums[start + 1])
                    {
                        start++;
                    }
                    // Discard matching adjacent values backward to eliminate identical combinations
                    while(end > 0 && nums[end] == nums[end - 1])
                    {
                        end--;
                    }
                    // Step standard pointers firmly outward onto unique candidate options
                    start++;
                    end--;
                }
                // Target sum value is too low; move the lower bound rightward to pick up larger sorted integers
                else if(sum < 0)
                {
                    start++;
                }
                // Target sum value is too high; step the upper bound leftward to pick up smaller sorted integers
                else if(sum > 0)
                {
                    end--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Str15 solver = new Str15();
        
        // Test Case 1: Standard unsorted mix with multi-triplet matches
        System.out.println("Test 1 ([-1,0,1,2,-1,-4]): " + solver.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        
        // Test Case 2: Zero values structure
        System.out.println("Test 2 ([0,0,0]): " + solver.threeSum(new int[]{0, 0, 0}));
        
        // Test Case 3: No matching combinations possible
        System.out.println("Test 3 ([0,1,1]): " + solver.threeSum(new int[]{0, 1, 1}));
        
        // Test Case 4: Positive values only
        System.out.println("Test 4 ([1,2,3,4]): " + solver.threeSum(new int[]{1, 2, 3, 4}));
        
        // Test Case 5: Large variants tracking
        System.out.println("Test 5 ([-2,0,0,2,2]): " + solver.threeSum(new int[]{-2, 0, 0, 2, 2}));
    }
}