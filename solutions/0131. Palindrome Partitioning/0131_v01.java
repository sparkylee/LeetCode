class Solution {
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
    private void partition(String s,List<List<Integer>> pals,int i, List<List<String>> plds,List<String>  palindrome)
    {
        if(i>=s.length())
        {
            plds.add(new ArrayList(palindrome));
            return;
        }
        List<Integer> pal = pals.get(i);
        for(Integer j : pal)
        {
            palindrome.add(s.substring(i,j+1));
            partition(s,pals,j+1,plds,palindrome);
            palindrome.remove(palindrome.size()-1);
        }
    }
    public List<List<String>> partition(String s) {
        List<List<Integer>> pals = new ArrayList<>();
        for(int i=0;i<s.length();i++)
        {
            pals.add(new ArrayList<>());
            pals.get(i).add(i);
        }
        for(int i=0;i<s.length();i++)
        {
            List<Integer> pal = pals.get(i);
            for(int j = i+1;j < s.length();j++)
            {
                if(isPalindrome(s,i,j))
                    pal.add(j);
            }
        }
        List<List<String>> result = new ArrayList<>();
        partition(s,pals,0,result,new ArrayList<>());
        return result;
    }
}