# Longest Common Prefix

- Write a function to find the longest common prefix string amongst an array of strings.
- If there is no common prefix, return an empty string "".

## Exmaple

- Input: ["flower","flow","flight"]
- Output: "fl"

## Solution

- 极其简单

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        int index = 0;
        StringBuilder stringBuilder = new StringBuilder("");
        Character character = '0';
        boolean flag = true;
        while (strs.length > 0 && flag && index < strs[0].length()) {
            character = strs[0].charAt(index);
            for (String string : strs) {
                if (index >= string.length()) {
                    flag = false;
                    break;
                }
                if (character != string.charAt(index)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                stringBuilder.append(character);
                index++;
            }
        }
        return stringBuilder.toString();
    }
}
```

## Other Solution