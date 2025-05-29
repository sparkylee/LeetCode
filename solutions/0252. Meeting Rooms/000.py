class Solution:
    def canAttendMeetings(self, intervals: List[List[int]]) -> bool:
        intervals.sort(key=lambda x: x[0])
        if len(intervals) == 0 or len(intervals) == 1: return True
        prev = intervals[0][1]
        for i in range(1, len(intervals)):
            if intervals[i][0]<prev: return False
            prev = intervals[i][1]
        return True