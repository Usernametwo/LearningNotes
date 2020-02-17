# ZigZag Conversion

- The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
    ```
    P   A   H   N
    A P L S I I G
    Y   I   R
    ```
- And then read line by line: "PAHNAPLSIIGYIR"
- Write the code that will take a string and make this conversion given a number of rows:
> string convert(string s, int numRows);

## Solution

- 暴力
- 智商亟待充值

```java
class Solution {
    public String convert(String s, int numRows) {
        Character[][] results = new Character[s.length()][numRows];
        int k = 0;
        for (int i = 0 ; i < s.length() ; i++) {
            for (int j = 0 ; j < numRows && k < s.length() ; j++) {
                results[i][j] = s.charAt(k++);
            }
            for (int j = numRows - 2 ; j > 0 && k < s.length() ; j--) {
                results[++i][j] = s.charAt(k++);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int j = 0 ; j < numRows ; j++) {
            for (int i = 0 ; i < s.length() ; i ++) {
                if (results[i][j] != null) {
                    result.append(results[i][j]);
                }
            }
        }
        return result.toString();
    }
}
```

## Other Solution

- 找规律
- 遍历字符串，判断出每一个字符所处的行，为每一行建一个StringBuilder,最后合并
```java
class Solution {
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }
}
```