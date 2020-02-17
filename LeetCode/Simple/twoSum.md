# Two Sum

- Given an array of integers, return `indices` of the two numbers such that they add up to a specific target.
- You may assume that each input would have `exactly` one solution, and you may not use the same element twice.

## Example

- Given nums = [2, 5, 5, 11], target = 10,

- Because nums[1] + nums[2] = 5 + 5 = 10,
- return [1, 2].

## Solutions
- 一个元素最多只能出现两次，而且当出现两次的时候target必定是这两个元素的和，否则会出现多个结果
- 还得考虑到target是一个元素的两倍的情况
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0 ; i < nums.length ; i++) {
            if(map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + i);
            } else {
                map.put(nums[i], i);
            }
        }
        for(int i = 0 ; i < nums.length ; i++) {
            int key = target - nums[i];
            if (key == nums[i] && i == map.get(key)) {
                continue;
            }
            if (key == nums[i]) {
                return new int[] {i, map.get(nums[i]) - i};
            }
            if (map.containsKey(key)) {
                return new int[]{i, map.get(key)};
            }
        }
        return new int[] {};
    }
}
```

## Best Solutions
- 没有必要一开始把所有的元素都装进HashMap里面
```java
public int[] twoSum(int[] nums, int target) {
    int[] result = new int[2];
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i=0; i< nums.length; i++) {
        if (map.containsKey(target-nums[i])) {
            result[0] = i;
            result[1] = map.get(target-nums[i]);
            break;
        }
        map.put(nums[i], i);
    }
    return result;
}
```