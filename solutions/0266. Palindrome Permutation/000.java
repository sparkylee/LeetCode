class Solution {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i< s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int odds = 0;
        for(int count: map.values()) {
            if(count%2==1) {
                odds ++;
            }
        }
        return odds <= 1;
    }
}