# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        phead = None;
        p = None;
        q = None;
        while l1 != None or l2 != None:
            if l1 != None and l2 != None:
                if l1.val <= l2.val:
                    q = l1;
                    l1 = l1.next;
                else:
                    q = l2;
                    l2 = l2.next;
            elif l1 == None:
                q = l2;
                l2 = l2.next;
            elif l2 == None:
                q = l1;
                l1 = l1.next;

            if phead == None:
                phead = q;
                p = phead;
            else:
                p.next = q;
                p = p.next;
        return phead;




