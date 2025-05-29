class Solution:
    def shortestDistance(self, wordsDict: List[str], word1: str, word2: str) -> int:
        index1, index2 = -1, -1
        ans = float('inf')
        for i in range(len(wordsDict)):
            if wordsDict[i] == word1: index1=i
            if wordsDict[i] == word2: index2=i
            if index1 != -1 and index2 != -1:
                ans = min(ans, abs(index1-index2))
        return ans