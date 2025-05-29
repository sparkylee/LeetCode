class Solution {
public:
    int hammingDistance(int x, int y) {
     int z = x ^ y;
     int cnt = 0;
     for(int i = 0; i < 32; i++)
     {
         if( (z & (0x1<<i)) != 0)
             cnt ++;
     }
     return cnt;
    }
};