"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, next=None):
        self.val = val
        self.next = next
"""

class Solution:
    def insert(self, head: 'Optional[Node]', insertVal: int) -> 'Node':
        if head is None:
            newnode = Node(insertVal)
            newnode.next = newnode
            return newnode

        prev, curr = head, head.next
        while curr != head:
            if (prev.val<=insertVal <= curr.val ) or \
                    ((insertVal >= prev.val or insertVal <= curr.val) and prev.val > curr.val):
                break
            prev, curr = curr, curr.next
        prev.next = Node(insertVal, curr)
        return head