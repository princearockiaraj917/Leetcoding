/*LeetCode 680: Valid Palindrome II
Strategy: Use two pointers from both ends; on the first mismatch, check if skipping either the left or right character forms a palindrome.
Core Loop Condition: while(i < j)
Complexity: Time: O(n) - at most one full palindrome check after a mismatch | Space: O(1) - only pointer variables are used
*/
class Solution {
    public boolean validPalindrome(String s) 
    {
        int i=0;
        int j=s.length()-1;

        // Compare mirrored characters until the pointers cross.
        while(i<j)
        {
            // On the first mismatch, try skipping either side since only one deletion is allowed.
            if(s.charAt(i)!=s.charAt(j))
            {
                return (ispal(s,i+1,j) || ispal(s,i,j-1));
            }
            i++;
            j--;
        }

        // Reaching here means the string is already a palindrome.
        return true;
    }

    private boolean ispal(String s, int i, int j)
    {
        // Verify whether the remaining substring is a valid palindrome.
        while(i<j)
        {
            // Any mismatch proves this deletion path cannot form a palindrome.
            if(s.charAt(i)!=s.charAt(j))
            {
                return false;
            }
            i++;
            j--;
        }

        // All mirrored characters matched in the checked substring.
        return true;
    }
}

public class Str680
{
    public static void main(String[]args)
    {
        Solution s=new Solution();

        // Covers palindrome, single-deletion, failure, and edge-case scenarios.
        String testcase[]= {"racecar","deeee","eeeed","abca","abc","a"};

        // Execute the solution against each prepared test case.
        for(int i=0;i<testcase.length-1;i++)
        {
            System.out.print(s.validPalindrome(testcase[i]));
        }
    }
}