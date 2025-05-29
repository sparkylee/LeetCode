class Solution {
public:
    int add10(int num, int digi) //y = num*10+digi;
	{
		if (num >(INT_MAX / 10))
			return INT_MAX;
		if (num <(INT_MIN / 10))
			return INT_MIN;
		int lhs = num * 10;
		int rhs = digi;
		if (lhs >= 0 && rhs >= 0) {
			if (INT_MAX - lhs <= rhs) {
				return INT_MAX;
			}
		}
		else if (lhs < 0 && rhs < 0) {
			if (lhs <= INT_MIN - rhs) {
				return INT_MIN;
			}
		}
		return lhs + rhs;
	}

    int myAtoi(string str) {
        if (str.empty())
			return 0;
		int Sign = 1;
		int Num = 0;
		int i = 0;
		while (isspace(str[i]))
			i++;
		if (str[i] == '-')
		{
			Sign = -1;
			i++;
		}
		else if (str[i] == '+')
		{
			Sign = 1;
			i++;
		}
		for (; i < str.length(); i++)
		{
			if (str[i] >= '0' && str[i] <= '9')
			{
				int nn = (int)((str[i] - '0'));
				Num = add10(Num, nn*Sign);
				//Num = Num * 10 + nn;
			}			
			else
				break;
		}
		return Num;
    }
};