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
        qNode = pNode.next;
        
        while qNode != None :
            if qNode.val != pNode.val:                                
                pNode = pNode.next;
                pNode.val = qNode.val;
            qNode = qNode.next;
        pNode.next = None;                
        return head;    