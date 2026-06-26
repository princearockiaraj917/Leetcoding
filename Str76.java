public class Str76 {

    /*
    ------------------------------------------------------------
    Problem : 76. Minimum Window Substring
    Approach : Sliding Window + Frequency Arrays
    ------------------------------------------------------------

    Algorithm:
    1. Store the frequency of every character present in string 't'.
    2. Expand the sliding window by moving the right pointer.
    3. Add every visited character to the current window.
    4. Whenever the current window contains all required characters,
       start shrinking the window from the left.
    5. Update the minimum window whenever a smaller valid window is found.
    6. Continue until the right pointer reaches the end of the string.

    ------------------------------------------------------------
    Time Complexity:

    Let:
    n = length of string s
    m = length of string t

    Building frequency array      : O(m)

    Sliding Window Traversal      : O(n)

    compare() function
        - Checks 128 ASCII characters.
        - O(128) = O(1)

    Overall Time Complexity:

    O(m) + O(n × 1)
    = O(n + m)

    ------------------------------------------------------------
    Space Complexity:

    window[]     -> 128 integers
    substring[]  -> 128 integers

    Since both arrays have a fixed size,

    Space Complexity = O(1)

    ------------------------------------------------------------
    */

    public String minWindow(String s, String t)
    {
        // Stores frequency of characters in current window
        int window[] = new int[128];

        // Stores frequency of characters required from t
        int substring[] = new int[128];

        // Build frequency array for string t
        for(int i = 0; i < t.length(); i++)
        {
            substring[t.charAt(i)]++;
        }

        // Initialize sliding window pointers
        int left = 0;
        int start = 0;
        int min = Integer.MAX_VALUE;

        // Expand the window
        for(int right = 0; right < s.length(); right++)
        {
            // Include current character in window
            window[s.charAt(right)]++;

            // Shrink the window while it is valid
            while(compare(window, substring))
            {
                // Update minimum window
                if(min > (right - left + 1))
                {
                    min = right - left + 1;
                    start = left;
                }

                // Remove leftmost character
                window[s.charAt(left)]--;

                // Shrink the window
                left++;
            }
        }

        // No valid window found
        if(min == Integer.MAX_VALUE)
            return "";

        // Return the minimum window substring
        return s.substring(start, start + min);
    }

    /*
     * Checks whether the current window contains
     * all characters required by string t.
     *
     * Returns:
     * true  -> Valid window
     * false -> Invalid window
     */
    private boolean compare(int window[], int substring[])
    {
        for(int i = 0; i < 128; i++)
        {
            // If any required character is missing,
            // the window is invalid.
            if(window[i] < substring[i])
                return false;
        }

        // Every required character is present
        return true;
    }

    public static void main(String[] args)
    {
        MinimumWindowSubstring obj = new MinimumWindowSubstring();

        System.out.println("Test Case 1");
        System.out.println("Input : s = ADOBECODEBANC, t = ABC");
        System.out.println("Output: " + obj.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println();

        System.out.println("Test Case 2");
        System.out.println("Input : s = a, t = a");
        System.out.println("Output: " + obj.minWindow("a", "a"));
        System.out.println();

        System.out.println("Test Case 3");
        System.out.println("Input : s = a, t = aa");
        System.out.println("Output: " + obj.minWindow("a", "aa"));
        System.out.println();

        System.out.println("Test Case 4");
        System.out.println("Input : s = aa, t = aa");
        System.out.println("Output: " + obj.minWindow("aa", "aa"));
        System.out.println();

        System.out.println("Test Case 5");
        System.out.println("Input : s = abdcab, t = abc");
        System.out.println("Output: " + obj.minWindow("abdcab", "abc"));
    }
}