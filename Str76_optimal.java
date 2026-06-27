/*
------------------------------------------------------------------------------
Problem : 76. Minimum Window Substring
Difficulty : Hard

Approach:
Sliding Window + Frequency Arrays (Optimized)

Algorithm:
1. Store the frequency of every character in string 't'.
2. Count the number of distinct characters (required).
3. Expand the window by moving the right pointer.
4. If a character reaches its required frequency,
   increment formed.
5. When formed == required, the window is valid.
6. Shrink the window from the left while it remains valid.
7. Update the minimum window whenever a smaller valid window is found.
8. Return the smallest valid substring.

Time Complexity:
O(n + m)

n = length of s
m = length of t

Space Complexity:
O(1)

Reason:
Only two arrays of size 128 are used.
------------------------------------------------------------------------------
*/

public class Str76_optimal 
{

    // substring[] stores the frequency of characters in string 't'
    public String minWindow(String s, String t)
    {
        // Stores frequency of characters inside current window
        int window[] = new int[128];

        // Stores required frequency of characters from t
        int substring[] = new int[128];

        int left = 0;
        int right;

        // Stores starting index of minimum window
        int start = 0;

        // Stores minimum window length
        int min = Integer.MAX_VALUE;

        // Number of distinct characters in t
        int required = 0;

        // Number of distinct character requirements satisfied
        int formed = 0;

        // Build frequency array for string t
        for(int i = 0; i < t.length(); i++)
        {
            // First occurrence of this character
            if(substring[t.charAt(i)] == 0)
            {
                required++;
            }

            substring[t.charAt(i)]++;
        }

        // Expand the sliding window
        for(right = 0; right < s.length(); right++)
        {
            // Include current character into window
            window[s.charAt(right)]++;

            // If current character just reached
            // the required frequency
            if(substring[s.charAt(right)] > 0 &&
               window[s.charAt(right)] == substring[s.charAt(right)])
            {
                formed++;
            }

            // Window is valid
            while(formed == required)
            {
                // Update minimum answer
                if(min > right - left + 1)
                {
                    min = right - left + 1;
                    start = left;
                }

                // Remove leftmost character
                window[s.charAt(left)]--;

                // If removing this character breaks
                // its required frequency
                if(substring[s.charAt(left)] > 0 &&
                   window[s.charAt(left)] < substring[s.charAt(left)])
                {
                    formed--;
                }

                // Shrink window
                left++;
            }
        }

        // No valid window found
        if(min == Integer.MAX_VALUE)
            return "";

        return s.substring(start, start + min);
    }

    public static void main(String[] args)
    {
        Str76_optimal obj = new Str76_optimal();

        System.out.println("Test Case 1");
        System.out.println("Input : ADOBECODEBANC , ABC");
        System.out.println("Output: " +
                obj.minWindow("ADOBECODEBANC", "ABC"));
        // Expected : BANC

        System.out.println();

        System.out.println("Test Case 2");
        System.out.println("Input : a , a");
        System.out.println("Output: " +
                obj.minWindow("a", "a"));
        // Expected : a

        System.out.println();

        System.out.println("Test Case 3");
        System.out.println("Input : a , aa");
        System.out.println("Output: " +
                obj.minWindow("a", "aa"));
        // Expected : ""

        System.out.println();

        System.out.println("Test Case 4");
        System.out.println("Input : aa , aa");
        System.out.println("Output: " +
                obj.minWindow("aa", "aa"));
        // Expected : aa

        System.out.println();

        System.out.println("Test Case 5");
        System.out.println("Input : cabwefgewcwaefgcf , cae");
        System.out.println("Output: " +
                obj.minWindow("cabwefgewcwaefgcf", "cae"));
        // Expected : cwae
    }
}