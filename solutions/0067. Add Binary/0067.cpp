class Solution {
public:
    string addBinary(string a, string b) {
        string l = (a.length() >= b.length()) ? a : b;
		string s = (a.length() < b.length()) ? a : b;
		int c = 0;
		char so = '0';
        if (l.length() < 1)
			l = "0";

		for (int i = l.length() - 1; i >= 0; i--)
		{
			int j = s.length() - (l.length() - i);
			if (j < 0)
				so = '0';
			else
				so = s[j];
			l[i] = l[i] + so + c - '0' ;
			if (l[i] >= '2')
			{
				l[i] = l[i] - 2;
				c = 1;
			}
			else
			{
				c = 0;
			}

		}
		if (c == 1)
			l = "1" + l;
		return l;
    }
};