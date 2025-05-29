# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def deleteNodes(self, head: Optional[ListNode], m: int, n: int) -> Optional[ListNode]:
        dummy = ListNode(0, head)
        prev = dummy
        curr = head
        cnt = 0
        while curr:
            if cnt == m:
                cnt = 0
                j = 0
                while j < n and curr:
                    curr = curr.next
                    j+=1
                if j<n or curr is None:
                    prev.next = None
                    return dummy.next
                prev.next = curr
            cnt += 1
            curr = curr.next
            prev = prev.next
        return dummy.next