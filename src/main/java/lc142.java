import org.junit.Test;

public class lc142 {

    class ListNode {
          int val;
          ListNode next;
          ListNode(int x) {
              val = x;
              next = null;
          }
    }
    @Test
    public void test1()
    {
        test(new int []{3,2,0,-4},1 );
    }
    @Test
    public void test2()
    {
        test(new int []{1,2},0 );
    }
    @Test
    public void test3()
    {
        test(new int []{1},-1 );
    }
    @Test
    public void test4()
    {
        test(new int []{2},0 );
    }
    @Test
    public void test5()
    {
        test(new int []{1,2},1 );
    }
    private void test(int [] array,int pos)
    {
        ListNode head = createTestCase(array,pos);
        Solution s = new Solution();
        ListNode p = s.detectCycle(head);
        System.out.println(p==null?p:p.val);
    }
    private ListNode createTestCase(int [] array,int pos)
    {
        if(array==null || array.length<1) return null;
        ListNode [] lns = new ListNode[array.length];
        for(int i=0;i<array.length;i++)
        {
            lns[i] = new ListNode(array[i]);
        }
        for(int i=0;i<array.length-1;i++)
        {
            lns[i].next = lns[i+1];
        }
        if(pos>=0)
            lns[lns.length-1].next = lns[pos];
        return lns[0];
    }
    public class Solution {
        private int detectCycleLength(ListNode head)
        {
            if(head==null) return 0;
            ListNode target = head;
            int k=1;
            while(true){
                ListNode p = target;
                for(int i=0;i<k;i++)
                {
                    p=p.next;
                    if(p==null) return 0;
                    if(p==target) return i+1;
                }
                target = p;
                k=k*2;
            }

        }
        public ListNode detectCycle(ListNode head) {
            int cycleLen = detectCycleLength(head);
            if(cycleLen<=0) return null;
            ListNode p = head,q=head;
            for(int i=0;i<cycleLen;i++)
                q=q.next;
            while (q!=p)
            {
                p=p.next;
                q=q.next;
            }
            return p;
        }
    }
}
