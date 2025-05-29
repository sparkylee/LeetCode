class Solution {
    int calQuadratic(int x, int a, int b, int c) {
        return a*x*x + b*x +c;
    }
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int N = nums.length;
        int [] results = new int[N];        
        int [] results_unsorted =  new int[N];
        for(int i=0;i<nums.length;i++) {
            results_unsorted[i] = calQuadratic(nums[i],a,b,c);
        }
        if(a==0 || N==1) {
            for(int i=0;i<N;i++) {               
                int index = (b>=0) ? i:(N-1-i);
                results[index] = results_unsorted[i];                
            }
            return results;
        }
        int sign = a>0 ? 1:-1;
        int coef = 2*a*sign;
        int target = -b*sign;
        int axis = 0;
        for(;axis<N;axis++) {
            if(coef*nums[axis]>= target) {
                break;
            }
        }
        // System.out.println("axis="+axis + " axis_value=" + nums[axis]);
        int i_left = axis - 1, i_right = axis;
        int ii = sign>0?0:(N-1);        
        while(true) {
            if(i_left>=0 && i_right < N) {
                int value_left = results_unsorted[i_left];
                int value_right = results_unsorted[i_right];
                if(sign>0) {
                    int value = Math.min(value_left, value_right);
                    results[ii] = value;
                    i_left = value_left <= value_right ? i_left - 1 : i_left;
                    i_right = value_left > value_right ? i_right + 1 : i_right;
                    ii += sign;
                } else {
                    int value = Math.max(value_left, value_right);
                    results[ii] = value;
                    i_left = value_left >= value_right ? i_left - 1 : i_left;
                    i_right = value_left < value_right ? i_right + 1 : i_right;
                    ii += sign;
                }
                continue;
            }
            if(i_left>=0) {
               results[ii] = results_unsorted[i_left] ;
               i_left -= 1;
               ii += sign;
               continue;
            }
            if(i_right < N) {
               results[ii] = results_unsorted[i_right] ;
               i_right += 1;
               ii += sign;
               continue;
            }
            break;
        }
        return results;
    }
}