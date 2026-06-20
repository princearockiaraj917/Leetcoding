import java.util.*;

// LeetCode 49: Group Anagrams
// Strategy: Generate sorted character keys out of individual string records to group matching structural patterns together in a map lookup.
// Core Loop Condition: for(String s:strs)
// Complexity: Time: O(n * k * log(k)) where n is array capacity and k represents average text entry length boundaries | Space: O(n * k) for mapping details.
class Str49 {
    public List<List<String>> groupAnagrams(String[] strs) 
    {
        // Fail-safe validation block to catch null values or blank arrays instantly
        if(strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        
        for(String s : strs)
        {
            char[] ch = s.toCharArray();
            // Sorting creates a uniform alphabetical configuration, serving as an identical base key for all matching anagram variations
            Arrays.sort(ch);
            String key = new String(ch);

            // Append word straight into its existing matched key bucket list
            if(map.containsKey(key))
            {
                map.get(key).add(s);
            }
            // First time seeing this pattern; allocate a novel tracking list record container
            else
            {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(key, list);
            }
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Str49 solver = new Str49();
        
        // Test Case 1: Standard diverse mixed anagram cluster
        String[] test1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Test Case 1: " + solver.groupAnagrams(test1));
        
        // Test Case 2: Singular structural input option
        String[] test2 = {""};
        System.out.println("Test Case 2: " + solver.groupAnagrams(test2));
        
        // Test Case 3: Lone text content verification
        String[] test3 = {"a"};
        System.out.println("Test Case 3: " + solver.groupAnagrams(test3));
        
        // Test Case 4: Repeated identical entries tracking
        String[] test4 = {"abc", "abc", "bca"};
        System.out.println("Test Case 4: " + solver.groupAnagrams(test4));
        
        // Test Case 5: Non-overlapping vocabulary variations
        String[] test5 = {"hello", "world", "java"};
        System.out.println("Test Case 5: " + solver.groupAnagrams(test5));
    }
}