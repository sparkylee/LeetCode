package lc1xx;

import org.junit.Test;

public  class lc152
{
    @Test
    public void test1() {
        tc(new int[]{0});
        tc(new int[]{2, 3, -2, 4});
        tc(new int[]{-2, 0, -1});
        tc(new int[]{-2, 0, 0, -1});
        tc(new int[]{-2});
        tc(new int[]{3, -2});
        tc(new int[]{-2, 3});
        tc(new int[]{3, -2, 3});
        tc(new int[]{-2,-3,-4});
        tc(new int[]{-2,-3,4,-4,-5});

    }

    private void tc(int[] nums) {
        Solution s = new Solution();
        int result = s.maxProduct(nums);
        System.out.println(result);

    }

    class Solution {
        public int maxProduct(int[] nums) {
            if(nums==null || nums.length<1) return 0;
            int product = nums[0];
            int start=0,end = 0;
            int count = 0;
            while (true) {
                if (end == nums.length || nums[end] == 0) {
                    int section_product = 1;
                    if(end-start<=1)
                        section_product = nums[start];
                    else {
                        if (count % 2 == 0) {
                            for (int i = start; i < end; i++)
                                section_product *= nums[i];
                        } else {
                            int left_product = 1;
                            int left=start;
                            while (nums[left]>0){
                                left_product *= nums[left];
                                left++;
                            }
                            int right = end - 1;
                            int right_product = 1;
                            while(nums[right]>0)  {
                                right_product *= nums[right];
                                right--;
                            }
                            if(count==1)
                                section_product = Math.max(left_product,right_product);
                            else {
                                left_product *= nums[left];
                                left++;

                                right_product *= nums[right];
                                right--;

                                int middle_product  = 1;
                                for (int i = left; i <= right; i++)
                                    middle_product *=nums[i];
                                left_product  *= middle_product;
                                right_product *= middle_product;
                                section_product = Math.max(left_product,right_product);
                            }
                        }

                    }

                    product = Math.max(section_product,product);
                    if(end>=nums.length)
                        break;
                    product = Math.max(product,0);
                    start = end ;
                    do {
                        start++;
                        if (start >= nums.length) return product;
                    } while (nums[start] == 0);
                    end = start;
                    count = 0;
                }
                if(nums[end]<0) count++;
                end++;
            }

            return product;
        }
    }

}
