// LeetCode 209: Minimum Size Subarray Sum
// Strategy: Use the Sliding Window (Caterpillar Movement) technique by expanding the window until the target is reached, then shrinking it to find the smallest valid length.
// Core Loop Condition: while (end < nums.length) with inner condition while (sum >= target)
// Complexity: Time: O(n) - each element enters and leaves the window at most once | Space: O(1) - only constant extra variables are used

class Solution {
    public int minSubArrayLen(int target, int[] nums) 
    {
        int start=0;
        int end=0;
        int min=Integer.MAX_VALUE;
        int sum=0;

        // Expand the caterpillar window until the end pointer reaches the array boundary.
        while(end<nums.length)
        {
            // Include the current element in the active window.
            sum+=nums[end];

            // Shrink the window while it still satisfies the target condition.
            while(sum>=target)
            {
                // Record the smallest valid window found so far.
                min=Math.min(min,end-start+1);

                // Remove elements from the left to search for a tighter valid window.
                sum=sum-nums[start];
                start++;
            }

            end++;
        }

        // Return 0 when no valid subarray exists.
        if(min==Integer.MAX_VALUE)return 0;
        else return min;
    }
}

public class Str209 
{
    public static void main(String[]args)
    {

    
        Solution sol=new Solution();
        {
            int target[]={7,13,4,1,100};
            int test_cases[][]={{2,3,1,2,4,3},{1,1,1,1,1,1,1,1},{1,4,4},{0,0,0,0},{1,1,1,0}};
            for(int i=0;i<5;i++)
            {
                System.out.println(sol.minSubArrayLen(target[i],test_cases[i]));
            }
        }
    }
}
