class Solution {
    boolean isNext(char c, char c_next) {
        return (c_next-c+26)%26==1;
    }
    boolean checkNext(String s, int i) {
        return i==0 ? true: isNext(s.charAt(i-1), s.charAt(i));
    }
    public int findSubstringInWraproundString(String s) {    
        int total_count = 0;
        int [] seq_max = new int[26];        
        int i= 0 ;
        int count = 0;
        int start = i;
        boolean ended = false;
        while(true) {          
            if((ended = i>=s.length()) || !checkNext(s, i)) {
                int index = s.charAt(start) - 'a';
                seq_max[index] = Math.max(seq_max[index], count);
                count = 0;
                start = i;
                if(ended)
                    break;
            } 
            count++;
            i++;  
        }
        // System.out.println(Arrays.toString(seq_max));
        for(i=0;i<26;i++) {
            if(seq_max[i]<=1) {                
                continue;
            }
            for(int j=1;j<Math.min(26, seq_max[i]);j++) {
                int k=(i+j)%26;
                seq_max[k] = Math.max(seq_max[k], seq_max[i]-j);
            }
        }
        total_count = Arrays.stream(seq_max).sum();
        return total_count;
    }    
}