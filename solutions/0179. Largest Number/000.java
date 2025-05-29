class Solution {
    public String largestNumber(int[] nums) {
       ArrayList lcstrs = new ArrayList();
        for(int i=0;i<nums.length;i++)
        {
            lcstrs.add(Integer.toString(nums[i]));
        }
        Collections.sort(lcstrs, new Comparator<String>() {
            @Override
            public int compare(String p1, String p2) {
                int len = p1.length() + p2.length();
                for(int i = 0; i < len; i++)
                {
                    char p1c,p2c;
                    p1c = ( i < p1.length() ? p1.charAt(i) : p2.charAt(i-p1.length()));
                    p2c = ( i < p2.length() ? p2.charAt(i) : p1.charAt(i-p2.length()) );
                    if(p1c==p2c)
                        continue;
                    return p1c > p2c ? -1:1;
                }
                return 0;
            }
        });

        String result= String.join("", lcstrs);
        int i=0;
        for(;i < result.length() - 1; i++)
        {
            if (result.charAt(i) != '0')
                break;
        }
        return result.substring(i);
    }
}