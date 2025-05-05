class Solution {
public:
    int reverse(int x) {
     int y = 0;
		cout << x << endl;
		while ( x != 0)
		{
			int y1 = y;
			int y2 = y * 10;
			if ((y > 0 && (y2/10) < y) || (y < 0 && (y2/10) > y))
				return 0;
			y = y2 + (x % 10);
			if ((y2 > 0 && y < y2) || (y2 < 0 && y > y2))
				return 0;
			x = x / 10;
		}
		return y;
    }
};