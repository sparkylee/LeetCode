class Solution {
public:
    int strStr(string haystack, string needle) {
        if (needle.length() == 0)
			return 0;
		if ( needle.length() > haystack.length())
			return -1;
		int i = 0;
		while (i + needle.length() <= haystack.length())
		{	
			bool matched = true;
			for (int j = 0; j < needle.length(); j++)
			{
				if (needle[j] != haystack[i + j])
				{
					matched = false;
					break;
				}				
			}
			if (matched)
				return i;
			else
			{
				i++;
			}
		}
		return -1;
    }
};