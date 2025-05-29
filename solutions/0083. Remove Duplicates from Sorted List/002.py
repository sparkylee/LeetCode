# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @return a ListNode
    def deleteDuplicates(self, head):
        if head == None or head.next == None:
            return head;
        head.next = self.deleteDuplicates(head.next);
        if head.val == head.next.val:
            head.next = head.next.next;                
        return head;     