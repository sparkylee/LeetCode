# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def hasCycle(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        n = 0;
        p = head;
        while ( p!=None ):
            if p.val == 0:
                n = n + 1;
            else:
                n = 0;
            if n > 5:
                return True;
            p.val = 0;
            p = p.next;
        return False;