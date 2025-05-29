# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def rotateRight(self, head, k):
        """
        :type head: ListNode
        :type k: int
        :rtype: ListNode
        """
        n = 0;
        tail = head;

        if tail == None or k == 0:
            return head;
        while tail.next != None:
            tail = tail.next;
            n += 1;
        n += 1;
        k = (k%n);
        if k == 0:
            return head;
        m = n - k -1;
        nTail = head;
        for i in range(m):
            nTail = nTail.next;
        nHead = nTail.next;

        nTail.next = None;
        tail.next = head;
        return nHead;
        