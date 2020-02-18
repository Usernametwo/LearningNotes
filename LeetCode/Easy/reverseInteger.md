# Reverse Integer

- Given a 32-bit signed integer, reverse digits of an integer.

## Example

- Input: -123
- Output: -321

## Solution

- 简单，无需过多解释
- 注意溢出

```java
class Solution {
    public int reverse(int x) {
        long result = 0;
        while (x != 0) {
            result = x%10 + result * 10;
            x /= 10;
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) result;
    }
}
```

## Other Solution

- 直接使用标准库

```java
class Solution {
    public int reverse(int x) {
        String reverseString = new StringBuilder().append(Math.abs(x)).reverse().toString();
        try {
            return (x < 0) ? -1 * Integer.parseInt(reverseString) : Integer.parseInt(reverseString);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
```