
class Solution {
	
public:
	string shortestPalindrome(string s) {
		
		int len = 0;		
		for (int i = s.length() - 1; i >= 0 ; i--)
		{
			
			bool isP = true;
			for (int j = i / 2; j >= 0 ; j--)
			{
				if (s[j] != s[i - j])
				{
					isP = false;
					break;
				}
			}
			if (isP)
			{
				len = i + 1;
				break;
			}

		}
		if (len == s.length())
		{
			//cout << "same length" << endl;
			return s;
		}
		
		else
		{
			string s1 = s.substr(len);
			std::reverse(s1.begin(), s1.end());
			return s.insert(0,s1);
		}
		return "";
	}
};