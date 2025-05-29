class Solution {
    char [] cs = {'e','g','f','i','h','o','n','s','r','u','t','w','v','x','z'};
    int [] counts = new int[15];
    //'e': 1,3,5,7,8,9,0
    //'g': 8,
    //'f': 4,5
    //'i': 5,6,8,9
    //'h': 3,8
    //'o': 1,2,4,0
    //'n': 1,7,9
    //'s': 6,7
    //'r': 3,4,0
    //'u': 4
    //'t': 2,3,8
    //'w': 2
    //'v': 5,7
    //'x': 6
    //'z': 0


    public String originalDigits(String s) {
       String [] digits = {"zero","one","two","three","four","five","six","seven","eight","nine"};
       Map<Character, Set<Integer>> mset = new HashMap<>();
       for(int i=0;i<digits.length;i++) {
        String digit_str = digits[i];
        for(int j = 0;j<digit_str.length();j++) {
            char c = digit_str.charAt(j);
            Set<Integer> set = mset.getOrDefault(c,new HashSet<Integer>());
            set.add(i);
            mset.putIfAbsent(c, set);
        }
       }
       Map<Character, Integer> smap = new HashMap<>();
       for(int i=0;i<s.length();i++) {
            char c=s.charAt(i);
            int count = smap.getOrDefault(c, 0);
            smap.put(c, count+1);
       }
       int [] results = new int[10];
       while(!mset.isEmpty()){        
        for(Character c: mset.keySet()) {
            Set<Integer> set = mset.get(c);
            if(set.isEmpty()) {
                mset.remove(c);
                break;
            }
            if(set.size()==1) {
                int digit = set.iterator().next();
                int count = smap.getOrDefault(c,0);                                                   
                results[digit] = count;
                String digit_str = digits[digit];
                for(int k=0;k<digit_str.length();k++) {
                    char d = digit_str.charAt(k);
                    if(count>0) {
                        int d_count = smap.getOrDefault(d, 0);
                        d_count -= count;
                        smap.put(d, d_count);
                    }                
                    if(mset.containsKey(d)) {
                        mset.get(d).remove(digit);                            
                    }                        
                }
                
            }
        }
       }
       StringBuilder sb = new StringBuilder();
       for(int i=0;i<results.length;i++)
       {
            int count = results[i];
            
            for(int j=0;j<count;j++){
                char c = (char)(i+'0');
                sb.append(c);
            }
       }
       
       return sb.toString();
    }
}