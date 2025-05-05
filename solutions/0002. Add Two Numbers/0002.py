# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def addTwoNumbersCin(self, l1, l2, c):
        if l1 == None and l2 ==None:
            if c==0:
                return None;
            else:
                n = ListNode(c);
                return n;

        if l1 != None and l2 == None:
            sum = l1.val + c;
            n = ListNode(sum%10);
            n.next = self.addTwoNumbersCin(l1.next,None,sum/10);
            return n;
        if l1 == None and l2 != None:
            sum = l2.val + c;
            n = ListNode(sum%10);
            n.next = self.addTwoNumbersCin(l2.next,None,sum/10);
            return n;
        sum = (l1.val+l2.val+c);
        n = ListNode(sum%10);
        n.next = self.addTwoNumbersCin(l1.next,l2.next,sum/10);
        #l3.append(n);
        return n;

    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        return self.addTwoNumbersCin(l1,l2,0);

