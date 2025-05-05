
class Solution {
	struct Endpoint
	{
		int h;//height
		int i;//coordinate
	};
	vector<Endpoint> getNewHeight(vector<int>& height,int & M)
	{


		vector<Endpoint>  points;
		if (height.empty())
		{
			M = -1;
			return points;
		}
		M = 0;
		int hs = (int)height.size();
		for (int i = 0; i < hs; i++)
		{
			Endpoint pt;
			pt.h = height[i];
			pt.i = i;
			if (!points.empty() && height[i] >= points.back().h)
			{
				int ps = (int)points.size();
				for (int j = ps - 1; j > M; j--)
				{
					if (points[j].h <= height[i])
						points.pop_back();
					else
						break;
				}
			}
			points.push_back(pt);
			if (points[M].h < points.back().h)
				M = (int)(points.size()) - 1;
		}
		return points;

	}
public:
	int maxArea(vector<int>& height) {
		int M = 0;
		vector<Endpoint>  points;
		points = getNewHeight(height, M);
		int ps = (int)points.size();

		if (ps < 2)
			return 0;
		int area = 0;
		for (int i = M; i >= 0; i--)
		{
			for (int j = M; j < ps; j++)
			{
				int ax = (points[j].i - points[i].i)* std::min(points[j].h, points[i].h);
				if (ax > area)
					area = ax;
			}

		}
		return area;

	}
};