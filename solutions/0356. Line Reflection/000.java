class Solution {
    public boolean isReflected(int[][] points) {        
        Set<Long> set = new HashSet<>();
        for(int [] point : points) {
            long p0 = (long) point[0] << 1;
            long p1 = (long)point[1];
            long p_hash = (p0 & 0xffffffffL)   | (p1 << 32 );
            // System.out.println("p_hash="+Long.toHexString(p_hash));
            set.add(p_hash);
        }        
        long x_sum = 0;  
        long x_min = (long)(int)(set.iterator().next() & 0xffffffffL);
        long x_max = (long)(int)(set.iterator().next() & 0xffffffffL);
        for(Long p : set) {
            long p_x = (long)(int)(p & 0xffffffffL);
            x_min = Math.min(p_x, x_min);
            x_max = Math.max(p_x, x_max);
        }
      
        long x_center = (x_max-x_min)/2 + x_min;
        Map<Long, Integer> map = new HashMap<>();
        for(Long p : set) {
            long p_x = (long)(int)(p & 0xffffffffL);
            long p_x_centered = p_x - x_center;
            if(p_x_centered==0)
                continue;
            int increment = p_x_centered>0?1:-1;
            p_x_centered = Math.abs(p_x_centered);
            long p_new = (p_x_centered & 0xffffffffL) | (p & 0xffffffff00000000L);
            int count = map.getOrDefault(p_new, 0);
            map.put(p_new, count+increment);            
        }
        for(Integer count: map.values()) {
            if(count!=0)
                return false;
        }
        return true;
    }
}