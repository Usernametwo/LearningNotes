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
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       int carry = 0;
        ListNode result = null;
        ListNode prev = null;
        while (!(l1==null && l2==null)) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            if (sum >= 10) {
                sum -= 10;
                carry = 1;
            } else{
                carry = 0;
            }
            if (result == null) {
                result = new ListNode(sum);
                prev = result;
                continue;
            }
            if (result != null) {
                ListNode node = new ListNode(sum);
                prev.next = node;
                prev = node;
            }
        }
        if (carry != 0) {
            ListNode node = new ListNode(carry);
            prev.next = node;
        }
        return result;
    }
}
```