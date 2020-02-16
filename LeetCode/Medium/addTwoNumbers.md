# Add Two Numbers
- You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
- You may assume the two numbers do not contain any leading zero, except the number 0 itself.

## Example
- Input: (2 -> 5 -> 9) + (5 -> 9)
- Output: 7 -> 4 -> 0 -> 1
- Explanation: 952 + 59 = 1074.

## Solution
- 注意进位
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

## Best Solution

暂未发现