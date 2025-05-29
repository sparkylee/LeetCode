class Solution {		
	float searchSortedArrays(vector<int>& nums1, int x1,int x2, vector<int>& nums2, int y1,int y2)
	{
		int xm = (x1 + x2)/2;
		int ym = (y1 + y2) / 2;
		
		if (x1 + 1 >= x2 && y1 + 1 >=y2)
			return (std::max(nums1[x1], nums2[y1]) + std::min(nums1[x2], nums2[y2])) / 2.0;
		if (nums1[xm + 1] >= nums2[ym] &&  nums2[ym+1] >= nums1[xm])
			return (std::max(nums1[xm], nums2[ym]) + std::min(nums1[xm+1], nums2[ym+1])) / 2.0;
		if (nums1[xm + 1] < nums2[ym])
			return searchSortedArrays(nums1,xm+1,x2,nums2,y1,ym);
		else
			return searchSortedArrays(nums1, x1, xm, nums2, ym+1, y2);
	}
public:
	double findMedianSortedArrays1(vector<int>& nums1, vector<int>& nums2) {	
		
		int n = nums1.size() ;		
		int x1, x2;
		int m = nums2.size();		
		int y1, y2;
		int z1,z2;
		z1 = (n - m) / 2;
		z2 = (n + m - 1) / 2;
		if (n >= m)
		{
			x1 = z1;
			x2 = z2;
			y1 = 0;
			y2 = m-1;
		}
		else
		{
			x1 = 0;
			x2 = n-1;
			y1 = -z1;
			y2 = z2;
		}

		float mv = searchSortedArrays(nums1, x1,x2, nums2, y1,y2);
		return mv;
	}
	double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) 
	//double findMedianMerge(vector<int>& nums1, vector<int>& nums2)
	{
		vector<int> nums;
		nums.insert(nums.end(), nums1.begin(), nums1.end());
		nums.insert(nums.end(), nums2.begin(), nums2.end());
		std::sort(nums.begin(), nums.end());
		int size = nums.size();
		if (size % 2 == 0)
		{
			return (nums[size/2 -1] + nums[size/2])/2.0;
		}
		else
		{
			return nums[size / 2];
		}

	}
};
