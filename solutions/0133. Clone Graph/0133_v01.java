/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null) return null;
        Map<Integer,UndirectedGraphNode> nodeMap_new = new HashMap<>(),nodeMap = new HashMap<>();
        UndirectedGraphNode head= new UndirectedGraphNode(node.label);
        nodeMap_new.put(node.label,head);
        nodeMap.putIfAbsent(node.label,node);
        List<UndirectedGraphNode> neighbors = node.neighbors;
        while(!neighbors.isEmpty()) {
            List<UndirectedGraphNode> neighbors_new = new ArrayList<>();
            for(UndirectedGraphNode n: neighbors) {
                nodeMap.putIfAbsent(n.label,n);
                if(!nodeMap_new.containsKey(n.label))
                {
                    UndirectedGraphNode nodeNew = new UndirectedGraphNode(n.label);
                    nodeMap_new.put(n.label,nodeNew);
                }
                for(UndirectedGraphNode next: n.neighbors)
                {
                    if(!nodeMap_new.containsKey(next.label)) {
                        neighbors_new.add(next);
                    }
                }
            }
            neighbors = neighbors_new;
        }
        for(Map.Entry<Integer,UndirectedGraphNode> entry: nodeMap.entrySet())
        {
            List<UndirectedGraphNode> nbs = entry.getValue().neighbors;
            UndirectedGraphNode node_new = nodeMap_new.get(entry.getKey());
            for(UndirectedGraphNode n: nbs)
                node_new.neighbors.add(nodeMap_new.get(n.label));

        }
        return head;
    }
}