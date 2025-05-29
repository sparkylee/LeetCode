class Solution:
    def minKnightMoves(self, x: int, y: int) -> int:
        directions = [(2, 1), (2, -1), (-2, 1), (1, 2), (-1, 2), (1, -2)]
        q= deque([[0, 0]])
        visited = set()
        visited.add((0, 0))
        minstep =0
        if x==0 and y==0: return 0
        x, y = abs(x), abs(y)
        while q:
            l = len(q)
            for _ in range(l):
                i, j = q.popleft()
                if i == x and j == y: return minstep
                for dx, dy in directions:
                    ii, jj = i + dx, j+dy
                    if (ii, jj) not in visited:
                        q.append([ii, jj])
                        visited.add((ii, jj))
            minstep+=1