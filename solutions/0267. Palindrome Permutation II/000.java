class Solution {
    void generatePermutation(Map<Character, Integer> map, int total_count, int current_count, StringBuilder sb, List<String> pmList)
    {
        if(total_count==current_count) {
            pmList.add(sb.toString());
            return;
        }
        Set<Character> set = map.keySet();
        for(char c: set) {
            int count = map.get(c);
            if(count!=0){                              
                map.computeIfPresent(c, (k,v)->(v-1));    
                sb.append(c);           
                generatePermutation(map, total_count, current_count+1,  sb, pmList);
                map.computeIfPresent(c, (k,v)->(v+1));
                if(sb.length()>0)
                    sb.deleteCharAt(sb.length()-1);
            }
            
        }
    }
    public List<String> generatePalindromes(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        List<String> results = new ArrayList<>();
        int odd_count = 0;
        char odd_char = 0;
        for(char c: map.keySet()) {
            int val = map.get(c);
            if(val%2==1) {
                odd_char = c;
                odd_count ++;
            }                            
        }
        if(odd_count>1)
            return results;
        String axis_str = "";
        if(odd_count==1) {
            axis_str = ("" + odd_char);
            map.computeIfPresent(odd_char, (k,v)->v-1);
        }
        int count = 0;
        for(char c: map.keySet()) {
           map.computeIfPresent(c, (k,v)->v/2);
           count += map.get(c);
        }
        List<String> pmList =  new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        // System.out.println("map is "+map);
        generatePermutation(map,count,0,sb,pmList);
        for(String pm: pmList){
            String pp = pm + axis_str +  (new StringBuilder(pm).reverse().toString());
            results.add(pp);
        }
        return results;
    }
}