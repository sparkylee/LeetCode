class Solution {
    public int lengthLongestPath(String input) {
        List<Integer> arr = new ArrayList();
        int arr_counter = 0; 
        int char_counter = 0;      
        boolean isFile = false;
        int mv = 0;     
        for(int i=0;i<=input.length();i++) {
            char c ;
            if(i==input.length() || (c=input.charAt(i))=='\n') {                
                arr = arr.subList(0, arr_counter);                                
                int pre_sum = (arr_counter == 0) ? 0: arr.getLast();
                int new_sum = pre_sum + char_counter;
                new_sum += isFile?0:1;
                arr.add(new_sum);
                if(isFile) {
                    mv = Math.max(mv, new_sum);
                    // System.out.println(arr);
                }
                arr_counter = 0;
                char_counter = 0;
                isFile = false;
                continue;
            }
            if(c=='\t') {
                arr_counter ++;
                continue;
            }
            if(c=='.')
                isFile = true;
            char_counter ++;

        }
        return mv;
    }
}