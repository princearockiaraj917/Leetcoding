import java.util.Arrays;

// LeetCode 3: Longest Substring Without Repeating Characters
// Strategy: Sliding Window track with an array map storing the last seen indices of characters to jump the window base forward upon duplicate discovery.
// Core Loop Condition: while(tail < s.length() && head < s.length())
// Complexity: Time: O(n) as the head pointer scans the string exactly once | Space: O(1) due to fixed 128-sized primitive ASCII index tracker array.
class Str3 {
    public int lengthOfLongestSubstring(String s) 
    {
        // Allocate fixed map for all standard ASCII character values
        int arr[] = new int[128];
        // Initialize with default values to signify unvisited tracking states
        Arrays.fill(arr, -1);
        int head = 0;
        int tail = 0;
        int max = 0;
        
        while(tail < s.length() && head < s.length())
        {
            // Character is novel or sits entirely before our current window boundary
            if(arr[s.charAt(head)] == -1 || arr[s.charAt(head)] < tail)
            {
                // Record index presence to establish future window jump states
                arr[s.charAt(head)] = head;
            }
            // Duplicate detected inside our active sliding window bounds
            else if (arr[s.charAt(head)] != -1)
            {
                // Shift window base immediately past the last recorded location of this duplicate
                tail = arr[s.charAt(head)] + 1;
                // Update tracker index to the current progressive scan point
                arr[s.charAt(head)] = head;
            }
            // Capture maximum length delta discovered during the sweep sequence
            max = Math.max(max, (head - tail + 1));
            head++;
        }
        return max;
    }

    public static void main(String[] args) {
        Str3 solver = new Str3();
        
        // Test Case 1: Standard repeating scenario
        System.out.println("Test Case 1 ('abcabcbb'): " + solver.lengthOfLongestSubstring("abcabcbb") + " (Expected: 3)");
        
        // Test Case 2: Identical structural characters
        System.out.println("Test Case 2 ('bbbbb'): " + solver.lengthOfLongestSubstring("bbbbb") + " (Expected: 1)");
        
        // Test Case 3: Mixed unique sequence at back
        System.out.println("Test Case 3 ('pwwkew'): " + solver.lengthOfLongestSubstring("pwwkew") + " (Expected: 3)");
        
        // Test Case 4: Empty sequence boundary condition
        System.out.println("Test Case 4 (''): " + solver.lengthOfLongestSubstring("") + " (Expected: 0)");
        
        // Test Case 5: Continuous alphanumeric string progression
        System.out.println("Test Case 5 ('au'): " + solver.lengthOfLongestSubstring("au") + " (Expected: 2)");
    }
}