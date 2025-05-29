class Solution:
    def minKnightMoves(self, x: int, y: int) -> int:
        directions = [(2, 1), (2, -1), (-2, 1), (-2, -1), (1, 2), (-1, 2), (1, -2), (-1, -2)]
        q= deque([[0, 0, 0]])
        visited = set((0, 0))
        while q:
            l = len(q)
            for _ in range(l):
                i, j, minstep = q.popleft()
                if i == x and j == y: return minstep
                for dx, dy in directions:
                    ii, jj = i + dx, j + dy
                    if (ii, jj) not in visited:
                        q.append([ii, jj, minstep + 1])
                        visited.add((ii, jj))
        return -1     