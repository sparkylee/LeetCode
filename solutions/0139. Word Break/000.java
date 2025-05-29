class Solution {
      public boolean wordBreak(String s, List<String> wordDict) {
            List<List<Integer>> wbList = new ArrayList<>();

            for(int i=0;i<s.length();i++)
            {
                List<Integer> posList = new ArrayList<>();
                for(int j = 0;j<wordDict.size();j++)
                {
                    String str = wordDict.get(j);
                    boolean matched = true;
                    for(int k=0;k<str.length();k++)
                    {
                        int ii = i+k;

                        if(ii>=s.length()|| str.charAt(k)!=s.charAt(ii))
                        {
                            matched = false;
                            break;
                        }
                    }
                    if(matched)
                    {
                        int nextPos = i+str.length();
                        posList.add(nextPos);
                    }
                }
                wbList.add(posList);
            }
            boolean [] wbBools = new boolean[s.length()+1];
            wbBools[s.length()]= true;
            for(int i=s.length()-1;i>=0;i--)
            {
                List<Integer> posList = wbList.get(i);
                for(Integer pos: posList)
                {
                    if(wbBools[pos])
                    {
                        wbBools[i] = true;
                        break;
                    }
                }
            }
            return wbBools[0];
        }
}