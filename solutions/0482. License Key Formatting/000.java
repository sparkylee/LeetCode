class Solution {
    public String licenseKeyFormatting(String S, int K) {
            int count = 0;
            for (int i = 0; i < S.length(); i++)
                count += S.charAt(i) == '-' ? 0 : 1;
            if(count==0) return "";
            int first_group_len = count % K;
            int dash_count = count / K;
            if (first_group_len == 0) {
                first_group_len = K;
                if(dash_count>0)
                    dash_count--;
            }
            int len_new = count + dash_count;

            int group_len = first_group_len;
            char[] chars = new char[len_new];
            int k = 0;
            int j = 0;
            while(k<group_len)
            {
                char c = S.charAt(j);
                if(c!='-')
                {
                    if(c>='a') c = (char)(c-'a'+'A');
                    chars[k]=c;
                    k++;
                }
                j++;
            }
            if(dash_count!=0) {
                chars[k] = '-';
                k++;
                dash_count--;
            }
            int group_index = 0;
            for (int i = j; i<S.length(); i++)
            {

                char c = S.charAt(i);
                if(c!='-')
                {
                    if(c>='a') c = (char)(c-'a'+'A');
                    chars[k]=c;
                    k++;
                    group_index++;
                }
                if(group_index==K)
                {
                    if(dash_count!=0) {
                        chars[k] = '-';
                        k++;
                        dash_count--;
                        group_index = 0;
                    }
                }
            }
            return new String(chars);
        
    }
}