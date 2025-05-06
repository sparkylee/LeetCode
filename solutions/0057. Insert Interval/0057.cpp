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
public:
    vector<Interval> insert(vector<Interval>& intervals, Interval newInterval) {
        vector<Interval> vi;
        bool inserted = false;
        for(int i = 0 ; i < intervals.size();i++)
        {
            if(inserted)
            {
                 vi.push_back(intervals[i]);
                continue;
            }
            bool isAhead = intervals[i].start > newInterval.end;
            bool isBehind = intervals[i].end < newInterval.start;
            if(isAhead || isBehind)
            {
                if(isAhead)
                {
                    vi.push_back(newInterval);
                    inserted = true;
                }
                vi.push_back(intervals[i]);
                continue;
            }
            newInterval.start = min(newInterval.start,intervals[i].start );
            newInterval.end   = max(newInterval.end,  intervals[i].end );
        }
        if(inserted==false)  vi.push_back(newInterval);
        return vi;
    }
};