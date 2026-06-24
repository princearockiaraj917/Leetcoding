import java.util.Arrays;

// LeetCode 744: Find Smallest Letter Greater Than Target
// Strategy: Linear scan through the array to find the smallest character strictly greater than the target ASCII value, reverting to the global minimum if no greater character exists.
// Core Loop Condition: for(int i=0;i<letters.length;i++)
// Complexity: Time: O(n) as it scans the input array up to two times sequentially | Space: O(1) as it uses a fixed set of primitive variables.
class Str744 {
    public char nextGreatestLetter(char[] letters, char target) 
    {
        int x = target;
        int min = Integer.MAX_VALUE;
        
        for(int i = 0; i < letters.length; i++)
        {
            int letter_asci = letters[i];
            // Track the character that is strictly greater than the target but represents the smallest option found so far
            if(letter_asci > x && letter_asci < min)
            {
                min = letter_asci;
            }
        }
        // Fallback boundary safety: no character in the array is greater than the target
        if(min == Integer.MAX_VALUE)
        {
            for(int i = 0; i < letters.length; i++)
            {
                int y = letters[i];
                // Loop through to find the absolute smallest wrap-around character in the entire array
                if(y < min)
                {
                    min = y;
                }
            }  
        }

        return (char)min;
    }

    public static void main(String[] args) {
        Str744 solver = new Str744();
        
        // Test Case 1: Standard case where greater characters exist immediately
        System.out.println("Test Case 1 (['c','f','j'], target='a'): " + solver.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'a') + " (Expected: c)");
        
        // Test Case 2: Target lies directly inside the array elements scale
        System.out.println("Test Case 2 (['c','f','j'], target='c'): " + solver.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'c') + " (Expected: f)");
        
        // Test Case 3: Target is greater than or equal to all elements (triggers the wrap-around fallback logic)
        System.out.println("Test Case 3 (['x','y','y'], target='z'): " + solver.nextGreatestLetter(new char[]{'x', 'y', 'y'}, 'z') + " (Expected: x)");
        
        // Test Case 4: Target matches the upper bound element (triggers wrap-around logic to return the first element)
        System.out.println("Test Case 4 (['c','f','j'], target='j'): " + solver.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'j') + " (Expected: c)");
        
        // Test Case 5: Duplicate elements tracking sequence
        System.out.println("Test Case 5 (['e','e','e','n','n'], target='e'): " + solver.nextGreatestLetter(new char[]{'e', 'e', 'e', 'n', 'n'}, 'e') + " (Expected: n)");
    }
}