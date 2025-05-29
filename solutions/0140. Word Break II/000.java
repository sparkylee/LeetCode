class Solution {
     private void generateResult(List<List<Integer>> wbList,int i,List<Integer> current,List<List<Integer>> result,List<String> wordDict)
        {
            if(i>=wbList.size())
            {
                result.add(new ArrayList<>(current));
                return;
            }
            List<Integer> words = wbList.get(i);
            for(Integer w: words )
            {
                current.add(w);
                generateResult(wbList,i+wordDict.get(w).length(),current,result,wordDict);
                current.remove(current.size()-1);
            }
        }
        public List<String> wordBreak(String s, List<String> wordDict) {
            List<List<Integer>> wbList = new ArrayList<>();
            for(int i=s.length()-1;i>=0;i--)
                wbList.add(new ArrayList<>());
            for(int i=s.length()-1;i>=0;i--)
                for(int w =0;w<wordDict.size();w++)
                {
                    String word = wordDict.get(w);
                    boolean matched = true;
                    for(int k=0;k<word.length();k++)
                    {
                        int j=i+k;
                        if(j>=s.length() || s.charAt(j)!=word.charAt(k))
                        {
                            matched = false;
                            break;
                        }
                    }
                    int jj = i+word.length();
                    boolean connected = (jj==s.length()) ||(jj<s.length() && !wbList.get(jj).isEmpty());
                    if(matched && connected)  wbList.get(i).add(w);
                }

            List<List<Integer>> resultInt = new ArrayList<>();
            List<Integer> current = new ArrayList<>();
            generateResult(wbList,0,current,resultInt,wordDict);
            List<String> resultStr= new ArrayList<>();
            for(List<Integer> r: resultInt)
            {
                StringBuilder sb = new StringBuilder();
                for(int w: r)
                {
                    sb.append(wordDict.get(w));
                    sb.append(' ');
                }
                sb.deleteCharAt(sb.length()-1);
                resultStr.add(sb.toString());
            }
            return resultStr;
        }
}