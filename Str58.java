/*LeetCode 58: Length of Last Word
Strategy: Traverse backward to skip trailing spaces, then count characters of the last word.
Core Loop Condition: while(i >= 0 && ch[i] == ' ') to strip spaces; then loop until next space.
 Complexity: Time: O(N) single pass backward | Space: O(N) for the character array allocation.
*/
class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        
        char ch[] = s.toCharArray();
        int len = 0;
        int i = ch.length - 1;

        // Step 1: Skip all trailing spaces from the end
        while (i >= 0 && ch[i] == ' ') {
            i--;
        }
        
        // Step 2: Count characters of the last word
        for (int j = i; j >= 0; j--) {
            if (ch[j] == ' ') {
                break;
            } else {
                len++;
            }
        }
        return len;
    }
}

public class Str58 {
    public static void main(String[] args) {
        Solution solve = new Solution();

        
        String[] testInputs = {
            "Hello World",          // 1.Standard case
            "   fly me   to   the moon  ", // 2. Trailing and multiple internal spaces
            "luffy is still joyboy",// 3. Normal sentence with no trailing spaces
            "a",                    // 4.(single character)
            "       "               // 5. Only spaces
        };

        int[] expectedResults = {5, 4, 6, 1, 0};

       
        System.out.println("--- Running Tests for Length of Last Word ---");
        for (int i = 0; i < testInputs.length; i++) {
            int result = solve.lengthOfLastWord(testInputs[i]);
            if (result == expectedResults[i]) {
                System.out.println("Test Case " + (i + 1) + ":PASSED");
            } else {
                System.out.println("Test Case " + (i + 1) + ":FAILED (Expected " + expectedResults[i] + ", Got " + result + ")");
            }
        }
    }
}