# Roman to Integer

- Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
    ```
    Symbol       Value
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000
    ```
- For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

- Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

    - I can be placed before V (5) and X (10) to make 4 and 9. 
    - X can be placed before L (50) and C (100) to make 40 and 90. 
    - C can be placed before D (500) and M (1000) to make 400 and 900.
- Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

## Exmaple

- Input: "LVIII"
- Output: 58
- Explanation: L = 50, V= 5, III = 3.


- Input: "MCMXCIV"
- Output: 1994
- Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

## Solution

- 逻辑简单，但是写的很啰嗦

```java
class Solution {
    public int romanToInt(String s) {
        int result = 0;
        RomanNumbers[] romanNumbers = RomanNumbers.values();
        int i = 0, j = 0;
        while (i < s.length()) {
            RomanNumbers romanNumber = romanNumbers[j];
            if (i + romanNumber.toString().length() > s.length()) {
                j++;
                continue;
            }
            String letter = s.substring(i, i + romanNumber.toString().length());
            if (letter.equals(romanNumber.toString())) {
                result += romanNumber.getCarry();
                i += letter.length();
            } else {
                j++;
            }
        }
        return result;
    }
}

enum RomanNumbers {

    M(1000),
    CM(900),
    D(500),
    CD(400),
    C(100),
    XC(90),
    L(50),
    XL(40),
    X(10),
    IX(9),
    V(5),
    IV(4),
    I(1);

    private int carry;

    private RomanNumbers (int n) {
        this.carry = n;
    }

    public int getCarry() {
        return carry;
    }

}
```

## Other Solution

- 没必要把所有的情况列举出来

```java
class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);        
        int temp1, temp2 = 0;
        int size = s.length();
        int result = 0;
        boolean skip = false;
        for(int i = 0; i < size; i++){
            if(skip){
                skip = false;
                continue;
            }
            temp1 = map.get(s.charAt(i));
            if( i < size -1){
                temp2 = map.get(s.charAt(i+1));
                if(temp1 >= temp2)
                    result += temp1;
                else{
                    result += temp2 - temp1;
                    skip = true;
                }
            }else{
                result += map.get(s.charAt(i));
            }
        }
        return result;
    }
}
```