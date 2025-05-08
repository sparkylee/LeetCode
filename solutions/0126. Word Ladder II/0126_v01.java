class Solution {
    List<Set<Integer>> reachables;
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
    private void findLadders(List<List<String>> ladders, List<String> wordList, List<String> strList, Set<Integer> positions,
                             List<Set<Integer>> reachables_new,int i, int depth)
    {
        if(i>=depth)
        {
            ladders.add(new ArrayList<>(strList)); return;
        }
        if(positions.isEmpty())  return;

        for(int pos: positions)
        {
            strList.add(wordList.get(pos));
            findLadders(ladders,wordList,strList,reachables_new.get(pos),reachables_new,i+1,depth);
            strList.remove(strList.size()-1);
        }
        return;

    }
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        reachables = new ArrayList<>();
        int len = wordList.size();
        for(int i=0;i<len;i++)
            reachables.add(new HashSet<>());
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
        List<Set<Integer>> jumps = new ArrayList<>();
        while (true) {
            if(candidates.isEmpty()) break;
            markChecked(checked, candidates);
            if(!Collections.disjoint(candidates,targets))
            {
                jumps.add(candidates);
                found = true;
                count++;
                break;
            }
            Set<Integer> nextJumps = new HashSet<>();
            for(int candidate: candidates)
            {
                Set<Integer> nextReachables = reachables.get(candidate);
                for(int reach:nextReachables)
                {
                    if(!checked[reach])
                        nextJumps.add(reach);

                }
            }
            jumps.add(candidates);
            candidates = nextJumps;
            count++;
        }

        List<List<String>> ladders = new ArrayList<>();
        if(!found) return ladders;

        jumps.get(jumps.size()-1).retainAll(targets);



        List<Set<Integer>> reachables_new =new ArrayList<>();
        for(int i=0;i<len;i++)
            reachables_new.add(new HashSet<>());
        for(int i=jumps.size()-2;i>=0;i--)
        {
            Set<Integer> indice =  jumps.get(i);
            Set<Integer> indice_pre =  jumps.get(i+1);
            for(int index: indice)
            {
                Set<Integer> reaches = reachables.get(index);
                Set<Integer> reaches_new = reachables_new.get(index);
                for(int r : reaches)
                {
                    if(indice_pre.contains(r)) reaches_new.add(r);
                }
            }
        }
        findLadders(ladders,wordList,new ArrayList<>(),jumps.get(0),reachables_new,0,jumps.size());
        for(List<String> ld : ladders)
        {
            ld.add(beginWord);
            Collections.reverse(ld);
        }
        return ladders;
    }
}