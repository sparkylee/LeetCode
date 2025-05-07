class Solution(object):
    def deleteDuplicates(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        while head and head.next and head.val == head.next.val:
            v = head.val;
            while head and head.val == v:
                head = head.next;
        p = head;
        while p:
            q = p.next;
            if q and q.next and q.val == q.next.val:
                v = q.val;
                while q and q.val == v:
                    q = q.next;
            else:
                p = q;
                continue;
            p.next = q
        return head;