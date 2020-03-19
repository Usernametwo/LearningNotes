# Regular Expression Matching

- Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
> '.' Matches any single character.

> '*' Matches zero or more of the preceding element.
- The matching should cover the entire input string (not partial).

## Example

- Input:
- s = "aa"
- p = "a"
- Output: false
- Explanation: "a" does not match the entire string "aa".

- Input:
- s = "ab"
- p = ".*"
- Output: true
- Explanation: ".\*" means "zero or more (\*) of any character (.)".

## Solution

- 动态规划，状态转移方程，注意边界条件，注意一定要从后往前匹配
- 应该还有一种 NFA->DFA 的解法，后续补充

```java
class Solution {
    /**
     * @return string.matches(pattern)
     */
     public boolean isMatch(String s, String p) {
        int[][] result = new int[s.length() + 1][p.length() + 1];
        return isMatch(0, 0, s, p, result);
    }

    /**
     * @return string[i:].matches(p[j:])
     * curMatch = (s[i] == s[j] || s[j] == '.')
     * match(i, j) = curMatch && match(i + 1, j + 1)  if p[j + 1] != '*'
     * match(i, j) = (curMatch && match(i + 1, j)) || match(i, j + 2) if p[j + 1] == "*"
     */
    private boolean isMatch(int i, int j, String s, String p, int result[][]) {
        if (i == s.length() && j == p.length()) {
            return true;
        } else if (i != s.length() && j == p.length()) {
            return false;
        }
        if (result[i][j] != 0) {
            return result[i][j] == 1;
        }
        boolean curMatch = (i < s.length()) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            curMatch = (curMatch && isMatch(i + 1, j, s, p, result)) || isMatch(i, j + 2, s, p, result);
        } else {
            curMatch = curMatch && isMatch(i + 1, j + 1, s, p, result);
        }
        result[i][j] = curMatch ? 1 : -1;
        return curMatch;
    }
}
```

## Other Solution

