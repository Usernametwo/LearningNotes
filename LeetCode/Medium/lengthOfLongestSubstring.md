# Longest Substring Without Repeating Characters

- Given a string, find the length of the longest substring without repeating characters.

## Example

- Input: "abcabcbb"
- Output: 3 
- Explanation: The answer is "abc", with the length of 3. 

## Solution
- 滑动窗口

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0, ans = 0;
        while (i<s.length() && j<s.length()) {
            if (set.contains(s.charAt(j))) {
                set.remove(s.charAt(i++));
            } else {
                set.add(s.charAt(j++));
                ans = Math.max(j - i, ans);
            }
        }
        return ans;
    }
}
```

## Best Solution

- 使用HashMap直接记录下标，如果有重复字符直接将滑动窗口的i下标移动至重复的字符所在
- HashMap可以直接使用数组代替，不过思路基本一样

```java
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}
```