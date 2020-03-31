# Letter Combinations of a Phone Number

- Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

- A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

## Exmaple

- Input: "23"
- Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

## Solution

- 暴力

```java
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        results.add("");
        List<String> realResult = new ArrayList<>();
        for (int i = 0 ; i < digits.length(); i++) {
            realResult = new ArrayList<>();
            Character[] letters = getLettersFromNumber(digits.charAt(i));
            for (Character letter : letters) {
                for (String result : results) {
                    realResult.add(result + letter);
                }
            }
            results = realResult;
        }
        return realResult;
    }
    
    /**
     * 根据电话号码获取字母
     * @param number
     * @return
     */
    private Character[] getLettersFromNumber(char number) {
        if (number == '2') {
            return new Character[] {'a', 'b', 'c'};
        }
        if (number == '3') {
            return new Character[] {'d', 'e', 'f'};
        }
        if (number == '4') {
            return new Character[] {'g', 'h', 'i'};
        }
        if (number == '5') {
            return new Character[] {'j', 'k', 'l'};
        }
        if (number == '6') {
            return new Character[] {'m', 'n', 'o'};
        }
        if (number == '7') {
            return new Character[] {'p', 'q', 'r', 's'};
        }
        if (number == '8') {
            return new Character[] {'t', 'u', 'v'};
        }
        if (number == '9') {
            return new Character[] {'w', 'x', 'y', 'z'};
        }
        return new Character[]{};
    }

}
```

## Other Solution
