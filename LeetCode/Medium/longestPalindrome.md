# Longest Palindromic Substring

- Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
## Example

- Input: "babad"
- Output: "bab"
- Note: "aba" is also a valid answer.

## Solution

- 动态规划
- 每一个中心都试一遍(一共有2n-1个中心)
- P(i,j)=(P(i+1,j−1) and S[i] ==S[j])

```java
class Solution {
    public String longestPalindrome(String s) {
        String[] results1 = new String[s.length()];
        // 以一个字符为中心
        for (int i = 0 ; i < s.length() ; i++) {
            results1[i] = s.charAt(i) + "";
            for (int  j = i + 1 ; j < s.length() ; j++) {
                if (2*i - j >= 0 && s.charAt(j) == s.charAt(2*i-j)) {
                    results1[i] = s.charAt(2*i-j) + results1[i] + s.charAt(j);
                } else {
                    break;
                }
            }
        }

        // 以两个字符为中心
        String results2[] = new String[s.length()];
        for (int i = 0 ; i < s.length() - 1 ; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                results2[i] = "" + s.charAt(i) + s.charAt(i+1);
                for (int j = i + 2 ; j < s.length() ; j++) {
                    if (2*i + 1 - j >= 0 && s.charAt(2*i + 1 - j) == s.charAt(j)) {
                        results2[i] = s.charAt(2 * i + 1 - j) + results2[i] + s.charAt(j);
                    } else {
                        break;
                    }
                }
            }
        }
        String result = "";
        for (String string : results1) {
            if (string != null && result.length() <= string.length()) {
                result = string;
            }
        }
        for (String string : results2) {
            if (string != null && result.length() <= string.length()) {
                result = string;
            }
        }

        return result;
    }
}
```

## Other Solution
- 减少不必要的遍历

```java
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
```