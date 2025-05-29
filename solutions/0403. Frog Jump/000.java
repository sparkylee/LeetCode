class Solution {
    private int existStone(int[] stones,int start, int end, int unit)
        {
            if(start<0 || start>=stones.length || end<0 || end>=stones.length) return -1;
            if(end<=start+1)
            {
                if(stones[start]==unit) return start;
                if(stones[end]==unit) return end;
                return -1;
            }
            int middle = (start+end)/2;
            if(stones[middle]>=unit)
                return existStone(stones,start,middle,unit);
            else
                return existStone(stones,middle,end,unit);
        }
        int [] deltas = new int[]{-1,0,1};
        private boolean canCross(int[] stones,int i, Set<Integer> [] jumps,int k)
        {
            Set<Integer> jmps = jumps[i];
            if(k<=0 || jmps.contains(k)) return false;
            if(i==stones.length-1) return true;
            int unit = stones[i];
            jmps.add(k);
            for(int delta : deltas)
            {
                int nextK= k+delta;
                if(nextK<1) continue;
                int nextUnit = unit+nextK;
                int nextIndex= existStone(stones,i+1,stones.length-1,nextUnit);
                if(nextIndex==-1) continue;
                if(canCross(stones,nextIndex,jumps,nextK)) return true;
            }
            return false;
        }
        public boolean canCross(int[] stones) {
            Set<Integer> [] jumps = new HashSet[stones.length];
            for(int i=0;i<jumps.length;i++) jumps[i] = new HashSet<>();
                if(stones[1]!=1) return false;
            return canCross(stones,1,jumps,1);
        }
}