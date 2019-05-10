import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public  class lc179
{
    @Test
    public void ut1()
    {
        long m= toIndex(7);
        System.out.println(m);
    }
    @Test
    public void ut2()
    {
        Node node= toNode(72);
        System.out.println(node.m);
        System.out.println(node.n);
        System.out.println(node.nstr);
    }
    @Test
    public void ut3()
    {
        int[] nums = {3,30,34,5,9};
        System.out.println(largestNumber(nums));
    }
    @Test
    public void ut4()
    {
        int[] nums = {10,2};
        System.out.println(largestNumber(nums));
    }
    class Node implements Comparable<Node> {
        long m;
        int n;
        int zeros;
        String nstr;
        Node(long m, int n, int zeros, String nstr)
        {
            this.m = m;
            this.n = n;
            this.zeros = zeros;

            this.nstr = nstr;
        }
        @Override
        public int compareTo(Node o) {
            if( this.m > o.m) return 1;
            else if( this.m < o.m ) return -1;
            else
                return this.zeros - o.zeros;
        }
    }
    private static long maxInt = 9999999999L;
    private long toIndex(int n)
    {
        long m = n;
        while(true)
        {
            long tmp = m*10;
            if(tmp>=maxInt)
                break;
            m = tmp;
        }
        return m;
    }
    private Node toNode(int n)
    {
        long m = n;
        int zeros = 0;
        while(true)
        {
            long tmp = m*10;
            zeros ++;
            if(tmp>=maxInt)
                break;
            m = tmp;
        }
        return new Node(m, n, zeros, String.valueOf(n));
    }
    private Node [] toNodeArray(int [] nums)
    {
        if(nums==null)
            return null;
        Node [] nodes = new Node[nums.length];
        for(int i =0; i < nodes.length; i++)
        {
            nodes[i] = toNode(nums[i]);
        }
        return nodes;
    }
    private String toString(Node [] nodes)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = nodes.length - 1; i>=0; i--)
//        for(int i=0; i< nodes.length; i++)
        {
            sb.append(nodes[i].nstr);
        }
        return sb.toString();
    }

    public String largestNumber(int[] nums) {

        Node [] nodes = toNodeArray(nums);
        Arrays.sort(nodes);
        return toString(nodes);
    }

}
