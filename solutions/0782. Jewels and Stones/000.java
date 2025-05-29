class Solution {
    int c2i(char c)
    {
        if(c>='a' && c<='z') return c-'a';
        if(c>='A' && c<='Z') return c-'A'+26;
        return -1;
    }
    public int numJewelsInStones(String J, String S) {
        boolean [] jewels = new boolean [52];
        for(int i=0;i<J.length();i++)
        {
            char c = J.charAt(i);
            jewels[c2i(c)] = true;
        }
        int count = 0;
        for(int i=0;i<S.length();i++)
        {
            char c = S.charAt(i);
            if(jewels[c2i(c)])
            {
                count++;
            }
        }
        return count;
    }
}