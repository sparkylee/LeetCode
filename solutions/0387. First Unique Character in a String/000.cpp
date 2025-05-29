struct MyTable
{
	int count;
	int index;
};
class Solution {
public:
	MyTable tb[26];
	int firstUniqChar(string s) {
		for (int i = 0; i < 26; i++)
		{
			tb[i].count = 0;
			tb[i].index = -1;
		}
		for (int j = 0; j < s.length(); j++)
		{
			int ti = (int)(s[j] - 'a');
			if (ti<0 || ti > 25)
				return -1;
			if (tb[ti].count == 0)
				tb[ti].index = j;
			tb[ti].count++;
		}
		int ei = -1;
		for (int i = 0; i < 26; i++)
		{
			if (tb[i].count == 1)
			{
				if (ei == -1 || ei > tb[i].index)
					ei = tb[i].index;
			}
		}
		return ei;
	}

};