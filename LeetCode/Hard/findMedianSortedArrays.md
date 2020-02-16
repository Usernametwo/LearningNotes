# Median of Two Sorted Arrays

- There are two sorted arrays nums1 and nums2 of size m and n respectively.
- Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
- You may assume nums1 and nums2 cannot be both empty.

## Example
- nums1 = [1, 3]
- nums2 = [2]
- The median is 2.0

## Solution

- 归并一半数组
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        int[] nums3 = new int[size/2 + 1];
        int i = 0, j = 0;
        while (i + j < size/2 + 1) {
            if (i < nums1.length && (j >= nums2.length || nums1[i] < nums2[j])) {
                nums3[i+j] = nums1[i++];
            } else {
                nums3[i+j] = nums2[j++];
            }
        }
        if (size % 2 == 0) {
            return (nums3[size/2 - 1] + nums3[size/2]) / 2.0;
        } else {
            return nums3[size/2];
        }
    }
}
```

## Best Solution

暂未发现