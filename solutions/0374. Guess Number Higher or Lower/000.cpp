// Forward declaration of guess API.
// @param num, your guess
// @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
int guess(int num);

class Solution {
public:
    int guessNumber(int n) {
        int m1 = 1, m2 = n, m = m1;
        int g = -1;
        do
        {
            m = m1/2 + m2/2;
            if (m1%2==1 and m2%2==1)
                m ++;
            g = guess(m); 
            if(g == 1)
            {
                if(m1 == m and m2==m1+1)
                     m1 = m + 1;
                else
                     m1 = m;
            }
            else if(g == -1)
            {
                m2 = m;
            }
        }while(g != 0);
        return m;
    }
    
};