/*  LeetCode 151: Reverse Words in a String
Strategy: Scan the string backward with two pointers to extract words from right to left, skipping extra spaces dynamically.
 Core Loop Condition: while (j >= 0) to process the entire string string-bound from the end to the start.
 Complexity: Time: O(N) linear scan | Space: O(N) auxiliary space for the output StringBuilder.
*/
class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int j = s.length() - 1;

        while (j >= 0) {
            // Skip any trailing or intermediate spaces from the current index backward
            while (j >= 0 && s.charAt(j) == ' ') {
                j--;
            }

            int end = j;

            // Trace backward through characters to establish the boundary of the current word
            while (j >= 0 && s.charAt(j) != ' ') {
                j--;
            }

            int start = j + 1;

            // Ensure pointers captured a valid character block before attempting to modify the builder
            if (start <= end) {
                // If it is the initial word extracted, append it cleanly without a leading delimiter
                if (sb.length() == 0) {
                    sb.append(s.substring(start, end + 1));
                } else {
                    // Append exactly one space to separate distinct word groupings from right to left
                    sb.append(" ");
                    sb.append(s.substring(start, end + 1));
                }
            }
        }

        return sb.toString();
    }
}

public class Str151
{
    public static void main(String[] args)
    {
        String str[]={"   hi  broo","the sky is blue"," ","Prince",""};
        Solution sol=new Solution();
        for(int i=0;i<str.length;i++)
        {
            String ans=sol.reverseWords(str[i]);
            System.out.println(ans);
        }
        
    }
}