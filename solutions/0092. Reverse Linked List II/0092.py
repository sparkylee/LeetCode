class Solution(object):
    def reverseBetween(self, head, m, n):
        """
        :type head: ListNode
        :type m: int
        :type n: int
        :rtype: ListNode
        """
        pm_prev,pm_cur,mc = None,head,1;
        while mc < m:
            pm_prev = pm_cur;
            pm_cur =  pm_cur.next;
            mc += 1;

        nc = mc;
        pn_prev = None;
        pn = pm_cur;
        pn_next = pn.next;
        while nc <= n:
            pn.next = pn_prev;
            pn_prev = pn;
            pn = pn_next;
            pn_next = None if not pn_next else pn_next.next;
            nc +=1;
        pm_cur.next = pn;
        if not pm_prev:
            return pn_prev;
        else:
            pm_prev.next = pn_prev;
            return head