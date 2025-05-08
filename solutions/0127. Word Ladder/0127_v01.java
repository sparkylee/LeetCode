class Solution {
    List<List<Integer>> reachables;
    private boolean isJumpable(String word_i,String word_j)
    {
        int count = 0;
        for(int k=0;k<word_i.length();k++)
            if(word_i.charAt(k)!=word_j.charAt(k)) count++;
        return count==1;
    }

    private boolean isJumpable(List<String> wordList,int i,int j)
    {
        return isJumpable(wordList.get(i),wordList.get(j));
    }
    private void markChecked(boolean [] checked,Set<Integer> candidates)
    {
        for(int candidate: candidates) checked[candidate] = true;
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        reachables = new ArrayList<>();
        int len = wordList.size();
        for(int i=0;i<len;i++)
            reachables.add(new ArrayList<>());
        for(int i=0;i<len;i++) {
            for (int j = i+1; j < len; j++)
            {
                if(isJumpable(wordList,i,j))
                {
                    reachables.get(i).add(j);
                    reachables.get(j).add(i);
                }
            }
        }
        Set<Integer> targets = new HashSet<>(),candidates = new HashSet<>();
        for(int i=0;i<len;i++)
        {
            if(isJumpable(beginWord,wordList.get(i)))
                targets.add(i);
            if(endWord.equals(wordList.get(i)))
                candidates.add(i);
        }
        boolean [] checked = new boolean[wordList.size()];
        int count = 0;
        boolean found = false;
        while (true) {
            if(candidates.isEmpty())
                break;
            markChecked(checked, candidates);
            if(!Collections.disjoint(candidates,targets))
            {
                found = true;
                count++;
                break;
            }
            Set<Integer> nextJumps = new HashSet<>();
            for(int candidate: candidates)
            {
                List<Integer> nextReachables = reachables.get(candidate);
                for(int reach:nextReachables)
                {
                    if(!checked[reach])
                        nextJumps.add(reach);

                }
            }
            candidates = nextJumps;
            count++;
        }

        return found?(count+1):0;
    }
}