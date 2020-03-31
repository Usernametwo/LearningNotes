# 3Sum Closest

- Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

## Exmaple

- Given array nums = [-1, 2, 1, -4], and target = 1.

- The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

## Solution

- 二分搜索找到最接近的第三个值，算法复杂度为pow(n, 2) * logn

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
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
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (nums[j] == jTemp) {
                    continue;
                }
                int twoSum = nums[i] + nums[j];
                int threeIndex = binarySearchCloest(nums, target - twoSum, j + 1, nums.length - 1);
                if (Math.abs(target - (twoSum + nums[threeIndex])) < Math.abs(target - result)) {
                    result = twoSum + nums[threeIndex];
                }

                jTemp = nums[j];
            }
            iTemp = nums[i];
            jTemp = Integer.MAX_VALUE;
        }

        return result;
    }

    /**
     * 二分搜索查找最接近value的值
     * @param nums
     * @param value
     * @param startIndex
     * @param endIndex
     * @return
     */
    private int binarySearchCloest(int[] nums, int value, int startIndex, int endIndex) {
        if (startIndex + 1 >= endIndex) {
            return Math.abs(value - nums[startIndex]) < Math.abs(value - nums[endIndex]) ? startIndex : endIndex;
        }
        int midIndex = (startIndex + endIndex) / 2;
        if (nums[midIndex] < value) {
            return binarySearchCloest(nums, value, midIndex + 1, endIndex);
        } else {
            return binarySearchCloest(nums, value, startIndex, midIndex);
        }
    }
}
```

## Other Solution

- 直接固定一个值，然后在排序的数组里面找到第二个和第三个值，算法复杂度为pow(n, 2)

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = 0;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int diff = Math.abs(target - sum);
                if (minDiff > diff) {
                    minDiff = diff;
                    result = sum;
                }
                if (diff == 0) {
                    return result;
                }
                if (target > sum) {
                    left ++;
                } else {
                    right --;
                }
            }
        }
        return result;
    }
}
```