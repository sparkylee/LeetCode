class Solution {
public:
    string convertToTitle(int n) {
       string ex = "";
		while (n != 0)
		{
			char c = 'A';
			int k = ( (n-1) % 26);
			char d = (c + k);
			stringstream ss;
			ss << d;
			string s;
			ss >> s;
			ex = ex + s;
			n = (n-1) / 26;
		}
		std::reverse(ex.begin(),ex.end());
		return ex;
    }
};