class Solution {
      public String minWindow(String s, String t) {
            if(t.length()<1 || s.length()<t.length()) return "";
            int [] TM = new int[256],SM = new int[256];
            for(int i=0;i<t.length();i++)
                TM[t.charAt(i)] += 1;
            for(int i=0;i<s.length();i++)
                SM[s.charAt(i)] += 1;

            for (int i=0;i<256;i++)
                if(SM[i]<TM[i]) return "";

            int start = 0, end = s.length() - 1;
            while (true) {
                char c = s.charAt(end);
                if (SM[c] >= TM[c] + 1) {
                    end--;
                    SM[c]--;
                } else
                    break;
            }

            int min = end- start +1;
            String winMin = s.substring(start,end+1);
            while (true)
            {
                start = shrinkStartIndex2(s,t,TM,SM,start);
                int len_new = end- start +1;
                if(len_new<min)
                {
                    min = len_new;
                    winMin = s.substring(start,end+1);
                }
                end++;
                if(end<s.length())
                    SM[s.charAt(end)]++;
                else break;
            }

            return winMin;
        }
        private int shrinkStartIndex2(String s, String t,int [] TM,int [] SM,int start)
        {
            while (true)
            {
                char c = s.charAt(start);
                if (SM[c] >= TM[c] + 1) {
                    start++;
                    SM[c]--;
                } else
                    break;
            }
            return start;
        }
}