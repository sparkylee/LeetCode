class Solution {
 public char findTheDifference(String s, String t) {
            int [] sg = new int[26], tg=new int[26];
            for(int i=0;i<26;i++)
            {
                sg[i]=0;
                tg[i]=0;
            }
            for(int i=0;i<s.length();i++) sg[s.charAt(i)-'a'] ++;
            for(int i=0;i<t.length();i++) tg[t.charAt(i)-'a'] ++;
            for(int i=0;i<26;i++)
            {
                if(sg[i]!=tg[i]) return (char)('a'+i) ;
            }
            return 'a';
        }
}