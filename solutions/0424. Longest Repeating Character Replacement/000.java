class Solution {
    public int characterReplacement(String s, int k) {
        int [] last_positions = new int[s.length()];   
        Arrays.fill(last_positions, -1);     
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {
                last_positions[i] = map.get(c);
            }
            map.put(c, i);
        }
        int longest_val_all = 0;
        for(char c: map.keySet()) {
            int end = map.get(c);
            int start = end;
            int kp = k;
            while(true) {
                int len = end - start + 1;
                int bonus = Math.min(s.length() - len , kp);
                int len_actual = len + bonus;
                longest_val_all = Math.max(len_actual, longest_val_all);
                int start_next = last_positions[start];
                if(start_next==-1) {
                    break;
                }
                int gap_start = start - start_next - 1;
                while(gap_start>kp) {
                    int end_next = last_positions[end];
                    int gap_end = end - end_next - 1;
                    kp += gap_end;
                    end = end_next;                    
                } 
                kp -= gap_start;
                start = start_next;
            }
        }
        return longest_val_all;

    }
}