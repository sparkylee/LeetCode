class Solution:
    def minKnightMoves(self, x: int, y: int) -> int:
        directions = [(2, 1), (2, -1), (-2, 1), (1, 2), (-1, 2), (1, -2)]
        q= deque([[0, 0, 0]])
        visited = set()
        visited.add((0, 0))

        if x==0 and y==0: return 0
        x, y, step = abs(x), abs(y), 0
        while q:
            i, j, step = q.popleft()
            if i == x and j == y: return step
            for dx, dy in directions:
                ii, jj = i + dx, j+dy
                if (ii, jj) not in visited:
                    q.append([ii, jj, step+1])
                    visited.add((ii, jj))