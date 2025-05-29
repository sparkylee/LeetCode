class Solution {
 private boolean isAnagrams(int [] pcount, int [] scount)
        {
            for(int i=0;i<26;i++)
                if(pcount[i]!=scount[i]) return false;
            return true;
        }
        public List<Integer> findAnagrams(String s, String p) {
            int [] pcount = new int[26],scount= new int[26];
            List<Integer> indices = new ArrayList<>();
            if(s.length()<p.length()) return indices;
            for(int i=0;i<p.length();i++) {
                pcount[p.charAt(i) - 'a']++;
                scount[s.charAt(i)-'a']++;
            }

            int k=0;
            while(true)
            {
                if(isAnagrams(pcount,scount))
                    indices.add(k);

                if(k<s.length()-p.length())
                {
                    scount[s.charAt(k)-'a']--;
                    scount[s.charAt(k+p.length())-'a']++;
                    k++;
                }
                else
                    break;
            }
            return indices;
        }
}