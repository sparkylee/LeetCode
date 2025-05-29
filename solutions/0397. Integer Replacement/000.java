class Solution {
    public int integerReplacement(int n) {
        if(n==1)
            return 0;
        long N = n;
        int count = 0;
        Set<Long> current_list = new HashSet();
        Set<Long> all_list = new HashSet();
        current_list.add(N);
        all_list.add(N);
        long [] new_val= new long[2];
        while(true) {            
            Set<Long> new_list = new HashSet();
            count++;
            for(long val : current_list){                                
                int size;
                if(val%2==0) {
                    size = 1;
                    new_val[0] = val/2;                                       
                } else {
                    size = 2;
                    new_val[0] = val+1;         
                    new_val[1] = val-1;         
                }
                for(int i=0;i<size;i++) {
                    if(new_val[i]==1)
                        return count;
                    if(!all_list.contains(new_val[i])) {
                        all_list.add(new_val[i]);
                        new_list.add(new_val[i]);
                    }                    
                }
            }
            // System.out.println(new_list.toString());
            current_list = new_list;
        }
        
    }
}