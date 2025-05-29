class Solution {
	vector<string> ve;
	void genPth(string str, int lp,int rp)
	{
		//  lp <= rp && lp >=0;			
		if (lp > 0)
			genPth(str+"(", lp - 1, rp);
		if (rp == 0)
			ve.push_back(str);
		else if (lp < rp)
			genPth(str+")", lp , rp -1);
		return;
	};
	void printPth()
	{
		for (int i = 0; i < ve.size(); i++)
		{
			cout << ve[i] << endl;
		}
	}
public:
	vector<string> generateParenthesis(int n) {
		ve.clear();
		genPth("", n, n);
		//printPth();
		return ve;
	}
};
