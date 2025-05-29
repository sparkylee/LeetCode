# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        def mergeTwoLists(l1, l2):
            dummy = curr = ListNode(0)
            while l1 and l2:
                if l1.val < l2.val:
                    curr.next = l1
                    l1 = l1.next
                else:
                    curr.next = l2
                    l2 = l2.next
                curr = curr.next
            if l1:
                curr.next = l1
            if l2:
                curr.next = l2
            return dummy.next

        def mergeSort(l, r):
            if l==r:
                return lists[l]

            m = (l+r)//2
            l1 = mergeSort(l, m)
            l2 = mergeSort(m+1, r)
            return mergeTwoLists(l1, l2)
        if not lists: return None
        return mergeSort(0, len(lists)-1)