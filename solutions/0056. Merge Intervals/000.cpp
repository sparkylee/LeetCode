/**
 * Definition for an interval.
 * struct Interval {
 *     int start;
 *     int end;
 *     Interval() : start(0), end(0) {}
 *     Interval(int s, int e) : start(s), end(e) {}
 * };
 */
class Solution {
    void printve(vector<int> v)
    {
        for(int i =0; i < v.size(); i++) cout<<v[i]<<" ";
        cout<<endl;        
    }
public:
    vector<Interval> merge(vector<Interval>& intervals) {
        vector<int> iv(intervals.size(),0);
        for(int i =0; i < iv.size(); i ++) iv[i] = i;
        for(int i =0; i < iv.size();i++)
        {
            for(int j = i+1; j < iv.size();j++ )
            {
                int tmp = iv[i];
                if(intervals[iv[i]].start > intervals[iv[j]].start)
                { 
                    iv[i] = iv[j];
                    iv[j] = tmp;                    
                }
            }
        }
        vector<Interval> ve;
        int i = 0;
       // printve(iv);
        while(i < intervals.size())
        {
            int k = i + 1;
            while( k < intervals.size() && intervals[iv[i]].end >= intervals[iv[k]].start )
            {
                intervals[iv[i]].end = max(intervals[iv[i]].end,intervals[iv[k]].end);
                k++;
            }
            ve.push_back(intervals[iv[i]]);            
            i = k;
        }
        return ve;
    }
};