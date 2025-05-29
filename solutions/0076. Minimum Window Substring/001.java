class Solution {
     private int shrinkStartIndex(String s, String t,Map<Character,Integer> TM,Map<Character,Integer> SM,int start)
        {
            while (true)
            {
                char c = s.charAt(start);
                int count = SM.get(c);
                int target = TM.getOrDefault(c,0);
                if (count >= target + 1) {
                    start++;
                    SM.put(c,SM.get(c)-1);
                } else
                    break;
            }
            return start;
        }
        public String minWindow(String s, String t) {
            if(t.length()<1 || s.length()<t.length()) return "";
            Map<Character,Integer> TM = new HashMap<>(),SM = new HashMap<>();
            for(int i=0;i<t.length();i++)
            {
                char c = t.charAt(i);
                TM.putIfAbsent(c, 0);
                TM.put(c,TM.get(c)+1);
            }
            for(int i=0;i<s.length();i++)
            {
                char c = s.charAt(i);
                SM.putIfAbsent(c, 0);
                SM.put(c,SM.get(c)+1);
            }

            for (Map.Entry<Character,Integer> entry : TM.entrySet())
            {
                char c = entry.getKey();
                int target = entry.getValue();
                int count = SM.getOrDefault(c, 0);
                if (count < target) return "";
            }

            int start = 0, end = s.length() - 1;
            while (true) {
                char c = s.charAt(end);
                int count = SM.get(c);
                int target = TM.getOrDefault(c,0);
                if (count >= target + 1) {
                    end--;
                    SM.put(c,SM.get(c)-1);
                } else
                    break;
            }

            int min = end- start +1;
            String winMin = s.substring(start,end+1);
            while (true)
            {
                start = shrinkStartIndex(s,t,TM,SM,start);
                int len_new = end- start +1;
                if(len_new<min)
                {
                    min = len_new;
                    winMin = s.substring(start,end+1);
                }
                end++;
                if(end<s.length())
                {
                    char c = s.charAt(end);
                    int count = SM.getOrDefault(c,0);
                    SM.put(c,count+1);
                }
                else break;
            }

            return winMin;
        }
}