class Solution {
    int [] cuts;
    private boolean isPalindrome(String s, int start, int end)
    {
        int k=0;
        while(true)
        {
            int i = start+k,j=end-k;
            if(j<i) break;
            if(s.charAt(i)!=s.charAt(j)) return false;
            k++;
        }
        return true;
    }
    private int minGroup(String s,List<List<Integer>> pals,int i)
    {
        if(i>=s.length())
        {
            return 0;//0 group means reaching the end of the string
        }
        int count = cuts[i];
        if(count>0) return count;

        List<Integer> pal = pals.get(i);
        for(Integer j : pal)
        {
            int c= minGroup(s,pals,j+1);
            if(count<0 ||  c<count) count = c;
        }
        cuts[i] = count+1;
        return count+1;


    }
    public int minCut(String s) {
        List<List<Integer>> pals = new ArrayList<>();
        for(int i=0;i<s.length();i++)
        {
            pals.add(new ArrayList<>());
            pals.get(i).add(i);
        }
        cuts = new int[s.length()];
        for(int i=0;i<s.length();i++)
            cuts[i]=-1;//-1 uninitialized
        for(int i=0;i<s.length();i++)
        {
            List<Integer> pal = pals.get(i);
            for(int j = i+1;j < s.length();j++)
            {
                if(isPalindrome(s,i,j))
                    pal.add(j);
            }
        }

        return minGroup(s,pals,0)-1;

    }
}