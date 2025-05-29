class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        int [] coef = {1,-1};
        if(nums[0]==0)
              map.put(0, 2);      
        else {
            map.put(nums[0], 1);
            map.put(-nums[0], 1);
        }
         
        for(int i = 1; i < nums.length; i++){
            // System.out.println("index: "+i+" ");
            int num = nums[i];
            Set<Integer> oldKeys = map.keySet();
            Map<Integer,Integer> mapNew = new HashMap<>();
            // System.out.println("keySet size: "+ oldKeys.size());
            for(int sum: oldKeys){
                for(int j=0;j<2;j++){
                    int newSum = sum + coef[j]*num;
                    // System.out.println("newSum="+newSum);        
                    int new_paths = map.get(sum);               
                    if (mapNew.containsKey(newSum)){                   
                       int existing_paths = mapNew.get(newSum);
                       int total_paths = new_paths + existing_paths;
                    //    System.out.println("existing_paths: "+existing_paths+" new_paths:"+new_paths); 
                       mapNew.put(newSum, total_paths);     
                    //    System.out.println("putting "+newSum+":"+total_paths);         
                    } else {
                        // System.out.println("putting "+newSum+":"+1);    
                        mapNew.put(newSum, new_paths);
                    }
                }
               
            }
            map = mapNew;         
            mapNew = null;   
            // for(int k : map.keySet()) {
            //       System.out.print(k+":"+map.get(k)+" ");
            // }
            // System.out.println("");
        }
        return map.containsKey(target)? map.get(target): 0 ;
    }
}