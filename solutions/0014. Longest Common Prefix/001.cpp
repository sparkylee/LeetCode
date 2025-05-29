class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
     	string lcp = "";
		int k = 0;
		for (int i = 0; i < strs.size(); i++)
		{
			if ( (i == 0) || (k > strs[i].length()))
				k = strs[i].length();			
		}
		char c;	
		if (k == 0)
			return "";
		for (int j = 0; j < k; j++)
		{
			for (int i = 0; i < strs.size(); i++)
			{
				if (i == 0)
					c = strs[i][j];
				else
				{
					if (c == strs[i][j])
						continue;
					else
						return strs[0].substr(0, j);
				}
			}
		}
		return strs[0].substr(0, k);		
    }
};