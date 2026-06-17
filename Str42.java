// LeetCode 42: Trapping Rain Water
// Strategy: Use two pointers and maintain left/right maximum heights, always processing the side with the smaller boundary since it determines the trapped water.
// Core Loop Condition: while (i < j) with branch condition if (height[i] <= height[j])
// Complexity: Time: O(n) - each index is processed at most once | Space: O(1) - only constant extra variables are used



class Solution {
    public int trap(int[] height) 
    { 
        // Handle empty input to avoid unnecessary processing.
        if(height.length==0)return 0;

        int i=0;
        int j=height.length-1;
        int left_max=0;
        int right_max=0;
        int total=0;

        while(i<j)
        {
            // The smaller boundary determines the maximum water that can be trapped.
            if(height[i]<=height[j])
            {
                // Update the highest wall seen so far from the left.
                if(height[i]>=left_max)
                {
                    left_max=height[i];
                }
                // Any shorter bar traps water up to left_max.
                else if(height[i]<left_max)
                {
                    total+=left_max-height[i];
                }

                i++;
            }
            // Process the right side when it is the limiting boundary.
            else if(height[i]>height[j])
            {
                // Update the highest wall seen so far from the right.
                if(height[j]>=right_max)
                {
                    right_max=height[j];
                }
                // Any shorter bar traps water up to right_max.
                else if(height[j]<right_max)
                {
                    total+=right_max-height[j];
                }

                j--;
            }
        }

        return total;
    }
}

public class Str42 
{
    public static void main(String args[])
    {
        Solution sol=new Solution();

        int test_case[][]={
            {0,1,0,2,1,0,1,3,2,1,2,1},
            {4,2,0,3,2,5},
            {0},
            {0,0,0,0,0,0},
            {1,1,1,1,1,1,1}
        };

        for(int i=0;i<test_case.length;i++)
        {
            System.out.println(sol.trap(test_case[i]));
        }
    }
}