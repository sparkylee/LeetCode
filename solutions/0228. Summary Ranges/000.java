class Solution {
     private String getStr(int start,int end)
        {
            if(start==end) return String.valueOf(start);
            return String.valueOf(start)+"->"+String.valueOf(end);
        }
        public List<String> summaryRanges(int[] nums) {
            List<String> summary = new ArrayList<>();
            if(nums==null || nums.length <1) return summary;
            int head = nums[0],tail = nums[0];
            for(int i=0;i<nums.length;i++)
            {
                if(nums[i]==tail) continue;
                if(nums[i]==tail+1)
                {
                    tail = nums[i];
                    continue;
                }

                summary.add(getStr(head,tail));
                head = nums[i];
                tail = nums[i];
            }
            summary.add(getStr(head,tail));
            return summary;
        }
}