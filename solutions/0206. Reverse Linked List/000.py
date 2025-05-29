# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def reverseList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if head == None or head.next == None:
            return head;
        phead = None;
        qhead = head;
        rhead = head.next;
        while rhead != None:
            qhead.next = phead;
            phead = qhead;
            qhead = rhead;
            rhead = rhead.next;
        qhead.next = phead;
        return qhead;
        