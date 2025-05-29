# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @return a ListNode
    def deleteDuplicates(self, head):
        pNode = head;
        while pNode != None and  pNode.next != None :
            if pNode.val == pNode.next.val :
                pNode.next = pNode.next.next;
            else:
                pNode = pNode.next;
        return head;
        