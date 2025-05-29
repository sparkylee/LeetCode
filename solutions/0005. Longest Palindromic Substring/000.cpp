
class Solution {
	 int len = 0, ck = 0, cj = 0;	
	 void searchPalindrome(int k, int j, int nlen, string s)
	 {
		 while (k >= 0 && j < s.length())
		 {
			 if (s[k] == s[j])
			 {
				 nlen += 2;
				 if (nlen > len)
				 {
					 len = nlen;
					 ck = k;
					 cj = j;
				 }
				 k--;
				 j++;								 
			 }
			 else
				return;
			 			
		 }
		 
		 return;
	 }
public:
	string longestPalindrome(string s) {
		len = 0, ck = 0, cj = 0;
		for (int i = 0; i < s.length(); i++)
		{			
			searchPalindrome(i , i , -1, s);
			searchPalindrome(i, i + 1, 0, s);
		}
		return s.substr(ck,len);
	}	
};
