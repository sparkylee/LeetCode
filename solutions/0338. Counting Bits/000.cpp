class Solution {
public:
    vector<int> countBits(int num) {
      vector<int> bit_vec;
      bit_vec.push_back(0);
      int k = 0,j=0,i = 0;
      i = (1<<k) + j;
      while(i<=num)
      {
          while(j<(1<<k) and i <= num)
            {
              bit_vec.push_back(bit_vec[j]+1);
              j++;
              i = (1<<k) + j;
            }
          k++;
          j = 0;
          i = (1<<k) + j;
	  }
      return bit_vec;
    }
};