import java.util.ArrayList;
import java.util.List;

public class lc797 {
    int[] reachabilities;

    private boolean allPathsSourceTarget(int[][] graph, int index,
                                         List<List<Integer>> results, List<Integer> candidate) {
        if (index == graph.length - 1) {
            results.add(new ArrayList<>(candidate));
            return true;
        }
        if (reachabilities[index] == -1) return false;
        boolean isReachable = false;
        if (reachabilities[index] == 1) isReachable = true;
        for (int node : graph[index]) {
            candidate.add(node);
            if (allPathsSourceTarget(graph, node, results, candidate))
                isReachable = true;
            candidate.remove(candidate.size() - 1);
        }
        reachabilities[index] = isReachable ? 1 : -1;
        return isReachable;
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> candidate = new ArrayList<>();
        reachabilities = new int[graph.length];
        allPathsSourceTarget(graph, 0, results, candidate);
        return results;
    }
}
