/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> intLL = new ArrayList<>();
            List<Node> nList = new ArrayList<>();
            if(root==null) return intLL;
            nList.add(root);
            levelOrderRecursive(nList,intLL);
            return intLL;
        }
        private void levelOrderRecursive(List<Node> nList,List<List<Integer>> intLL) {
            if(nList==null || nList.isEmpty()) return;
            List<Integer> iList = new ArrayList<>();
            List<Node> nList_new = new ArrayList<>();
            for(Node n: nList)
            {
                if(n!=null)
                {
                    iList.add(n.val);
                    nList_new.addAll(n.children);
                }
            }
            intLL.add(iList);
            levelOrderRecursive(nList_new,intLL);
        }
}