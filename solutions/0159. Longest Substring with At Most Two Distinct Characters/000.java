class Solution {
    void add(Map<Character,Integer> map, char c) {
        map.put(c, map.getOrDefault(c, 0) + 1);

    }
    void sub(Map<Character,Integer> map, char c) {        
        if(!map.containsKey(c))
            return;
        map.put(c, map.get(c) - 1);
        if(map.get(c)==0)
            map.remove(c);
    }
    public int lengthOfLongestSubstringTwoDistinct(String s) {        
        if(s.length()<=2) {
            return s.length();
        }
        int i = 0, j=1;      
        int max_len = 2;  
        Map<Character, Integer> map = new HashMap<>();
        add(map, s.charAt(i));
        add(map, s.charAt(j));
        j++;
        while( j < s.length() && i < j ) {
            while(j < s.length() && (map.size() < 2 || (map.size()==2 && map.containsKey(s.charAt(j))))) {
                add(map, s.charAt(j));
                int current_len = j + 1 - i;
                max_len = Math.max(max_len, current_len);
                j++;
            }
            if(j>=s.length())
                break;
            sub(map, s.charAt(i));
            i++;            
        }
        return max_len;
    }
}