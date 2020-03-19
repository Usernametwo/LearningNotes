# Container With Most Water

- Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

## Example

- Input: [1,8,6,2,5,4,8,3,7]
- Output: 49

## Solution

- 总是向内移动最小的那条边

```java
class Solution {
    /**
     * always move the shorter one
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int leftIndex = 0;
        int rightIndex = height.length - 1;
        return move(leftIndex, rightIndex, height);
    }

    private int move(int leftIndex, int rightIndex, int[] height) {
        int currentArea = (rightIndex - leftIndex) * Math.min(height[leftIndex], height[rightIndex]);
        if (leftIndex + 1 == rightIndex) {
            return currentArea;
        }
        if (height[leftIndex] < height[rightIndex]) {
            int nextArea = move(++leftIndex, rightIndex, height);
            return Math.max(currentArea, nextArea);
        } else {
            int nextArea = move(leftIndex, --rightIndex, height);
            return Math.max(currentArea, nextArea);
        }
    }
}
```

## Other Solution

- 没必要整些花里胡哨的递归

```java
public class Solution {
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }
}
```
