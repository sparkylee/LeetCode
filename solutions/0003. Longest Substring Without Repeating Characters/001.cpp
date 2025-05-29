class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        vector<bool> vt(256,false);
		int ci = 0, cj = 0, len = 0;
		int i = 0;
		int j = i;
		while (i<s.length())
		{			
			
			while (j<s.length())
			{
//				cout << s[j]<< "   " <<vt[s[j]] << endl;
				if (!vt[s[j]])
				{					
					vt[s[j]] = true;					
					j++;
					int nlen = j - i;
					if (nlen > len)
					{
						ci = i;
						cj = j;
						len = nlen;
					}
				}
				else
					break;
			}
			while (s[i] != s[j])
			{
				vt[s[i]] = false;
				i++;
			}  
			vt[s[i]] = false;
			i++;
		}
		return len;
    }
};