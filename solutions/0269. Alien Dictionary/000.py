class Solution:
    def alienOrder(self, words: List[str]) -> str:
        graph = {c: [] for word in words for c in word}
        for word1, word2 in zip(words, words[1:]):
            for char1, char2 in zip(word1, word2):
                if char1 != char2:
                    graph[char1].append(char2)
                    break
            else:
                if len(word2) < len(word1):
                    return ''

        def topological_sort(v):
            discovered.add(v)
            ret = True
            for w in graph[v]:
                if w in discovered: return False
                if w not in finished:
                    ret = topological_sort(w)
                if not ret: break

            ans.appendleft(v)
            finished.add(v)
            discovered.remove(v)
            return ret

        ans = deque([])
        discovered, finished = set(), set()
        for v in graph.keys():
            if v not in finished:
                if not topological_sort(v):
                    return ''
        return ''.join(ans)   