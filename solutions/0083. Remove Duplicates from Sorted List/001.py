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
        if pNode == None:
          return head;        
        while pNode.next != None :
            if pNode.next.val == pNode.val:
                pNode.next = pNode.next.next;
            else:
                pNode = pNode.next;
        return head;  