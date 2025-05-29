class Solution(object):
    def partition(self, head, x):
        """
        :type head: ListNode
        :type x: int
        :rtype: ListNode
        """
        smallList= [];
        bigList = []

        while head:
            if head.val < x:
                smallList.append(head.val);
            else:
                bigList.append(head.val);
            head = head.next;
        smallList.extend(bigList);
        return self.createListNodes(smallList);
    def createListNodes(self,nums):
        if not nums:
            return None;
        head = ListNode(nums[0]);
        tail = head;
        for i in range(1,len(nums)):
            node = ListNode(nums[i]);
            tail.next = node;
            tail = node;
        return head;