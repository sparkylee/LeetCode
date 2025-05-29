class Solution {
    public int magicalString(int n) {       
        List<Integer> ms =  new ArrayList<>();
        ms.add(1);
        ms.add(2);
        ms.add(2);        
        int count = 1;
        int i = 2;        
        while (ms.size()<n) {
            int group_size = ms.get(i);
            int last_value = ms.getLast();
            int next_value = 3 - last_value;
            for(int j=0; j<group_size;j++) {
                ms.add(next_value);
                count += (next_value==1 ? 1: 0);
                if(ms.size()==n)
                    return count;
            }
            i++;
        }
        return count;
    }
}