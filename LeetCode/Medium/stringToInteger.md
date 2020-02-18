# String to Integer (atoi)

## Example
- Input: "   -42"
- Output: -42
- Explanation: The first non-whitespace character is '-', which is the minus sign. Then take as many numerical digits as possible, which gets 42.

+ Input: "4193 with words"
+ Output: 4193
+ Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.


- Input: "words and 987"
- Output: 0
- Explanation: The first non-whitespace character is 'w', which is not a numerical digit or a +/- sign. Therefore no valid conversion could be performed.

+ Input: "-91283472332"
+ Output: -2147483648
+ Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer. Thefore INT_MIN is returned.

## Solution

- 没啥好说的，直接暴力完事

```java
class Solution {
    public int myAtoi(String str) {
        long result = 0L;
        int sign = 0;
        int startIndex = 0;
        for (int i = 0 ; i < str.length() ; i++) {
            char c = str.charAt(i);
            if (c == '+') {
                sign = 1;
                startIndex = i + 1;
                break;
            } else if (c == '-') {
                sign = -1;
                startIndex = i + 1;
                break;
            } else if (c <= '9' && c >= '0') {
                sign = 1;
                startIndex = i;
                break;
            } else if (c != ' ') {
                return 0;
            }
        }
        if (sign == 0) {
            return 0;
        }
        long number = 0L;
        for (int i = startIndex ; i < str.length() ; i++) {
            char c = str.charAt(i);
            if (c <= '9' && c >= '0') {
                number = number * 10 + c - '0';
                result = sign * number;
                if (result > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (result < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else {
                return (int)result;
            }
        }
        return (int) result;
    }
}
```