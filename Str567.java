import java.util.Arrays;

// LeetCode 567: Permutation in String
// Strategy: Fixed-size Sliding Window using character count matches across a sequence scale bounded explicitly by string entry s1 length metrics.
// Core Loop Condition: while(end<s2.length())
// Complexity: Time: O(n) where n represents length metrics tracked on s2 | Space: O(1) bounded by fixed 26-bucket allocation map layout sizes.
class Str567 {
    public boolean checkInclusion(String s1, String s2) 
    {
        // Fail-safe edge condition checks to verify window boundary possibilities match sizing structures
        if(s1.length() > s2.length()) return false;
        int window[] = freq(s1);
        int start = 0;
        int end = s1.length() - 1;
        // Build initial comparison framework matches out of matching string segments
        int s2freq[] = freq(s2.substring(0, s1.length()));
        // Direct initial evaluation match state verify checks
        if(Arrays.equals(window, s2freq)) return true;
        end++;
        
        while(end < s2.length())
        {
            // Track newly captured incoming character item references inside historical records array 
            s2freq[s2.charAt(end) - 'a']++;
            // Discard data context drop signatures pointing to historical left index positions dropped out of bounds
            s2freq[s2.charAt(start) - 'a']--;
            // Frequency fingerprints match perfectly, confirming a valid permutation sequence is present
            if(Arrays.equals(window, s2freq)) return true;
            start++;
            end++;
        }
        return false;
    }
    
    // Abstracted helper module designed explicitly to convert clean text sequences down into raw alphabetical frequency matrices
    private int[] freq(String s)
    {
        int freq_arr[] = new int[26];
        for(int i = 0; i < s.length(); i++)
        {
            char ch = s.charAt(i);
            freq_arr[ch - 'a']++;
        }
        return freq_arr;
    }

    public static void main(String[] args) {
        Str567 solver = new Str567();
        
        // Test Case 1: Valid substring match presence
        System.out.println("Test Case 1 (s1='ab', s2='eidbaooo'): " + solver.checkInclusion("ab", "eidbaooo") + " (Expected: true)");
        
        // Test Case 2: Disjoint broken variant configuration
        System.out.println("Test Case 2 (s1='ab', s2='eidboaoo'): " + solver.checkInclusion("ab", "eidboaoo") + " (Expected: false)");
        
        // Test Case 3: Direct continuous adjacent profile presence
        System.out.println("Test Case 3 (s1='adc', s2='dcda'): " + solver.checkInclusion("adc", "dcda") + " (Expected: true)");
        
        // Test Case 4: Length constraints invalid boundary profile
        System.out.println("Test Case 4 (s1='hello', s2='ohe'): " + solver.checkInclusion("hello", "ohe") + " (Expected: false)");
        
        // Test Case 5: Exact string parity layout structure matching
        System.out.println("Test Case 5 (s1='abc', s2='bca'): " + solver.checkInclusion("abc", "bca") + " (Expected: true)");
    }
}