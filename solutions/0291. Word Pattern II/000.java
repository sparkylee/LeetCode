class Solution {
    class Pattern {     
        int size;
        int count;
        Pattern( int size,int count) {
            this.size = size;
            this.count = count;
        }
    }
    boolean validatePattern(String pattern, Map<Character, Pattern> map, String s) {
        Map<Character, String> pmap = new HashMap<>();
        Set<String> set = new HashSet<>();
        int j = 0;
        for(int i=0;i<pattern.length();i++) {
            char c = pattern.charAt(i);
            Pattern p = map.get(c);   
            int endIndex = j+p.size;         
            String str ;
            if(!pmap.containsKey(c)) {
                str = s.substring(j, endIndex);
                pmap.put(c, str);
                if(set.contains(str))
                    return false;
                set.add(str);
                j =  endIndex;
                continue;
            }
            str = pmap.get(c);
            for(int k=0;k<str.length();k++){
                char sd = s.charAt(j);
                char pd = str.charAt(k);
                if(sd!=pd)
                    return false;
                j++;
            }                        
        }
        return true;
    }
    boolean wordPatternMatch(String pattern,  Character [] clist, int index, Map<Character, Pattern> map,  int sum, String s) {
        if(index >= clist.length)
            return false;
        char c = clist[index];
        Pattern p = map.get(c);
        while(true) {
            if(sum > s.length())
            {
                p.size = 1;
                return false;
            }
                
            if(sum==s.length()) {
                boolean result = validatePattern(pattern, map, s);
                if(!result)
                     p.size = 1;
                return result;
            }
            if(wordPatternMatch(pattern, clist, index+1, map, sum, s)) {
                return true;
            }
            p.size ++;
            sum += p.count;            
        }        
    }
    public boolean wordPatternMatch(String pattern, String s) {
        Map<Character, Pattern> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i); 
            Pattern p = map.getOrDefault(c, new Pattern(1, 0));
            p.count ++;
            sum += p.size;
            map.putIfAbsent(c, p);            
        }
        return wordPatternMatch(pattern, map.keySet().toArray(new Character[map.keySet().size()]), 0, map, sum, s);       
    }
}