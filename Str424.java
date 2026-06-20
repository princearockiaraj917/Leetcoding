// LeetCode 424: Longest Repeating Character Replacement
// Strategy: Sliding Window layout expanding tracking pointers while monitoring maximum character frequency context within the present window scope.
// Core Loop Condition: for(int i=0;i<n;i++)
// Complexity: Time: O(n) as the fast pointer checks each sequence location exactly once | Space: O(1) bounded by fixed 26-element alphabet tracking array.
class Str424 {
    public int characterReplacement(String s, int k) 
    {
        int arr[] = new int[26];
        int max_freq = 0;
        int left = 0;
        int max_window = 0;
        int n = s.length();
        char ch[] = s.toCharArray();
        
        for(int i = 0; i < n; i++)
        {
            // Record target instance occurrence inside uppercase lookup index bounds
            arr[ch[i] - 'A']++;
            // Dynamically maintain the highest count signature tracked within any window frame discovered so far
            max_freq = Math.max(max_freq, arr[ch[i] - 'A']);

            int window_length = i - left + 1;
            // Count of invalid characters inside current window bounds has breached allowed conversion budget k
            if(window_length - max_freq > k)
            {   
                // Evict tail tracking counts from historical collection record before pointer step
                arr[ch[left] - 'A']--;
                // Contract window left edge forward to realign with constraints rules
                left++;
                // Synchronize active size variable following left edge shift modifications
                window_length--;
            }
            // Save the largest validated sliding size configuration processed
            max_window = Math.max(window_length, max_window);
        }
        return max_window;
    }

    public static void main(String[] args) {
        Str424 solver = new Str424();
        
        // Test Case 1: Simple single shift allocation
        System.out.println("Test Case 1 ('ABAB', k=2): " + solver.characterReplacement("ABAB", 2) + " (Expected: 4)");
        
        // Test Case 2: Multi-character option paths
        System.out.println("Test Case 2 ('AABABBA', k=1): " + solver.characterReplacement("AABABBA", 1) + " (Expected: 4)");
        
        // Test Case 3: Fully uniform string structure
        System.out.println("Test Case 3 ('AAAA', k=0): " + solver.characterReplacement("AAAA", 0) + " (Expected: 4)");
        
        // Test Case 4: No allowance budget constraint check
        System.out.println("Test Case 4 ('ABCDE', k=1): " + solver.characterReplacement("ABCDE", 1) + " (Expected: 2)");
        
        // Test Case 5: Budget exceeds variation layout limits
        System.out.println("Test Case 5 ('XYZ', k=5): " + solver.characterReplacement("XYZ", 5) + " (Expected: 3)");
    }
}