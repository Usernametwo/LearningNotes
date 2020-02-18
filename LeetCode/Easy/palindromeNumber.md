# Palindrome Number

- Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

## Example

- Input: -121
- Output: false
- Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

## Solution

```java
class Solution {
    public boolean isPalindrome(int x) {
        int y = x;
        if (x < 0) {
            return false;
        }
        int reverse = 0;
        while (y != 0) {
            reverse = reverse * 10 + y%10;
            y /= 10;
        }
        return reverse == x;
    }
}
```

## Other Solution
- 翻转一半即可
```java
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x !=0 && x%10 == 0)) {
            return false;
        }
        int reverse = 0;
        while (x > reverse) {
            reverse = reverse * 10 + x%10;
            x /= 10;
        }
        return reverse == x ||x == reverse / 10;
    }
}
```