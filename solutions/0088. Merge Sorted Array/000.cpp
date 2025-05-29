class Solution {
public:
    void merge(vector<int>& nums1, int m, vector<int>& nums2, int n) {
     int j = m-1, k = n-1,i=m+n-1;
    /* if(nums1.size() < m + n)
     {
        nums1[0] = nums1.size();
        return;
     }*/
     nums1.resize(m+n);
     while(i>=0 and k>=0 ){
         if(j>=0 and nums1[j]>nums2[k])
         {
             nums1[i] = nums1[j];
             j--;
         }
         else
         {
             nums1[i] = nums2[k];
             k--;
         }
         i--;
     }
         
    }
};