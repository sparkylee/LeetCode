class Solution {
public:
    string convert(string s, int numRows) {
        	if (numRows <= 1)
			return s;
		int n = s.length();		
		string sc(n, '0');
		vector<int> ve(numRows,0);
		
		int ve_x = n / (numRows*2-2);
		int ve_y = n % (numRows * 2 - 2);
		ve[0] = ve_x ;
		ve[numRows -1] = ve_x ;
		for (int i = 1; i < ve.size() - 1; i++)
		{
			ve[i] = ve_x * 2;
		}
		int dir = 1;
		
		int jj = 0;
		while ( ve_y != 0)
		{
			ve[jj] ++;
			if ((jj == 0 && dir == -1) || ( (jj == numRows - 1) && dir == 1))
				dir = dir * (-1);
			jj += dir;
			ve_y--;
		}
		/////////////
		int sj = 0;
		int si = 0;
		int j = 0;
		while (j < n)
		{		
			int sc_j = 0;
			for (int i = 0; i < si; i++)
			{
				sc_j += ve[i];
			}		
			if (si == 0 || si == numRows - 1)
			{
				sc_j += sj / (numRows - 1) ;
			}
			else
			{
				sc_j += 2 * (sj / (numRows - 1));
				if (sj % (numRows - 1) != 0)
					sc_j++;
			}
		
			
			sc[sc_j] = s[j];

			int remain = j % (numRows * 2 - 2);
			
			if (remain < numRows -1 )
			{
				si++;
			}
			else
			{
				si--;
				sj++;
			}
			j++;
		}
		return sc;
    }
};