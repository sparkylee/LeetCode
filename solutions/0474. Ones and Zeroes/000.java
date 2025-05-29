class Solution {
    int ss_size = 0;
    int [][] sums;
    Map<Integer, Integer> map;
    int findMaxForm(int [][] nums, int start, int end, int m, int n) {
        if(m<0 || n<0 || start < 0 || end >= nums.length || start > end )
            return 0;
        if(m>=sums[start][0] && n>=sums[start][1]) {
            return end - start + 1;
        }
        int index = start<<16 | m << 8 | n;
        if(map.containsKey(index)) {
            return map.get(index);
        }
        int [] num = nums[start];
        int m_new = m - num[0];
        int n_new = n - num[1];
        int ss_size1 = 0;
        
        ss_size1 = findMaxForm(nums, start + 1, end, m, n);                
        // ss_size = Math.max(ss_size, ss_size1);
        int ss_size2 = 0;
        if(m>=num[0] && n>=num[1]) {
            ss_size2 = 1 + findMaxForm(nums, start + 1, end, m_new, n_new);
        }
        int ss_count = Math.max(ss_size1, ss_size2);
        map.put(start<<16 | m << 8 | n,ss_count);
        return ss_count;
    }
    public int findMaxForm(String[] strs, int m, int n) {
        this.map = new HashMap();
        int [][] nums = new int[strs.length][2];
        for(int i=0;i<strs.length;i++) {
            String str = strs[i];            
            for(int j=0;j<str.length();j++) {
                char c = str.charAt(j);
                nums[i][0] += c=='0'?1:0;
                nums[i][1] += c=='1'?1:0;
            }
        }
        sums = new int[strs.length][2];
        int sums_zero = 0;
        int sums_one = 0;
        for(int i=nums.length-1;i>=0;i--) {
            sums_zero += nums[i][0];
            sums_one  += nums[i][1];
            sums[i][0]  = sums_zero;
            sums[i][1]  = sums_one;
        }
        // System.out.println(Arrays.deepToString(nums));
        // System.out.println()
        return findMaxForm(nums,0, nums.length-1, m, n);
    }
}