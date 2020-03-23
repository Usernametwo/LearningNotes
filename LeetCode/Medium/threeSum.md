# Title

- Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

## Exmaple

- Given array nums = [-1, 0, 1, 2, -1, -4],

- A solution set is:
    ```
    [
        [-1, 0, 1],
        [-1, -1, 2]
    ]
    ```

## Solution

- 排序之后去重，注意边界条件
- 使用HashMap快速找到第三个数，从而使时间复杂度降为O(n*n)

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        Map<Long, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            numsMap.put((long)nums[i], i);
        }
        
        int iTemp = Integer.MAX_VALUE;
        int jTemp = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == iTemp) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == jTemp) {
                    continue;
                }
                long twoSum = nums[i] + nums[j];
                if (numsMap.containsKey(-twoSum) && (numsMap.get(-twoSum) > j)) {
                    List<Integer> result = new ArrayList<>();
                    result.add(nums[i]);
                    result.add(nums[j]);
                    result.add(nums[numsMap.get(-twoSum)]);
                    results.add(result);
                }
                jTemp = nums[j];
            }
            iTemp = nums[i];
            jTemp = Integer.MAX_VALUE;
        }

        return results;
    }
}
```

## Other Solution

- 
```java
```