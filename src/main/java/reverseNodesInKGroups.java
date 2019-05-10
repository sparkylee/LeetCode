import org.junit.Test;

public class reverseNodesInKGroups {
   class ListNode {
        ListNode(Integer x) {
            value = x;
        }
        Integer value;
        ListNode next;
   }

   @Test
   public void test1()
   {
        test(new int [] {1, 2, 3, 4, 5},2);
   }
    @Test
    public void test2()
    {
        test(new int [] {1, 2, 3, 4, 5},1);
    }
    @Test
    public void test3()
    {
        test(new int [] {1, 2, 3, 4, 5},3);
    }
    @Test
    public void test4()
    {
        test(new int [] {1, 2, 3, 4, 5},4);
    }
    @Test
    public void test5()
    {
        test(new int [] {1, 2, 3, 4, 5},5);
    }
    @Test
    public void test6()
    {
        test(new int [] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},3);
    }
    @Test
    public void test7()
    {
        test(new int [] {1,2},2);
    }
   private void test(int [] l, int k)
   {
       ListNode h = createLN(l);
       ListNode head = reverseNodesInKGroups(h,k);
       printListNode(head);
   }
   private void printListNode(ListNode head)
   {
       while(head!=null)
       {
           System.out.print(head.value+" ");
           head=head.next;
       }
   }
    private ListNode createLN(int [] array)
    {
        if(array==null || array.length<1 ) return null;
        ListNode [] lnArray = new ListNode[array.length];
        for(int i=0;i<array.length;i++)
        {
            lnArray[i] = new ListNode(array[i]);
        }
        for(int i=0;i<array.length-1;i++)
        {
            lnArray[i].next = lnArray[i+1];
        }
        return lnArray[0];
    }
    private boolean hasMoreThanK(ListNode head,int k)
    {
        int i=0;
        while(head!=null)
        {
            head=head.next;
            i++;
            if(i==k) return true;
        }
        return false;
    }
    ListNode reverseNodesInKGroups(ListNode l, int k) {
        if(k<=1) return l;
        ListNode head_back = l;
        if(!hasMoreThanK(head_back,k)) return l;

        ListNode head_front = null,tail_front = null;
        while(hasMoreThanK(head_back,k)) {

            int count = 0;
            ListNode p = head_back;
            ListNode ptail = p;
            ListNode q = p.next;
            ListNode phead = null;
            while (true) {
                p.next = phead;
                phead = p;
                count ++;
                p = q;
                if(count==k) break;
                q = q.next;
            }
            head_back=q;
            if(head_front==null)//the first time
            {
                head_front = phead;
                tail_front = ptail;
            }
            else//join the front
            {
                tail_front.next = phead;
                tail_front = ptail;
            }
        }
        if(tail_front!=null)
            tail_front.next = head_back;
        return head_front;
    }
}
