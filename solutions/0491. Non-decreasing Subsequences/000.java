class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> lst = new ArrayList<>();
        int count = 0;
        lst.add(new ArrayList<>());
        count++;
        for(int i=0;i<nums.length;i++){
            int size = lst.size();
            int num = nums[i];
            for(int j=0;j<size;j++) {
                List<Integer> ll = lst.get(j);
                boolean non_decreasing = ll.isEmpty() || ll.getLast() <= num;
                if(!non_decreasing) {
                    continue;
                }
                List<Integer> llNew = new ArrayList<>(ll);
                llNew.add(num);
                // System.out.println("checking "+ llNew);
                int hashCode = llNew.hashCode();
                boolean conflicted = map.containsKey(hashCode);
                boolean matched = false;
                List<Integer> indices;
                if(conflicted) {
                    indices = map.get(hashCode);                   
                    for(int k=0;k<indices.size();k++){
                        int index = indices.get(k);
                        if(llNew.equals(lst.get(index))) {
                            matched = true;
                            break;
                        }
                    }                        
                }
                if(matched) {
                    continue;
                }     
                lst.add(llNew);             
                if(conflicted){
                    map.get(hashCode).add(count);                
                } else {
                    indices = new ArrayList<>();
                    indices.add(count);
                    map.put(hashCode, indices);
                }
                count++;
            }
        }
        List<List<Integer>> lst2 = new ArrayList<>();
        for(List<Integer> ll : lst) {
            if(ll.size()>1) {
                lst2.add(ll);
            }
        }
        return lst2;
    }
}