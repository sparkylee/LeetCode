/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Codec {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root==null)
            return "";
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    private void serialize(Node root, StringBuilder sb) {
        if(root==null)
            return;
        sb.append(root.val);
        sb.append('(');
        for(Node child: root.children) {
            serialize(child, sb);
        }
        sb.append(')');
    }
	
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data==null | data.equals(""))
            return null;
        List<Node> nodes = new ArrayList<>();
        deserialize(data,0, nodes);
        return nodes.isEmpty()? null: nodes.getFirst();
    }
     // Decodes your encoded data to tree.
    public int deserialize(String data, int start, List<Node> children) {
        if(data==null)
            return start;
        int i = start;
        while(true) {
            if(i>=data.length()) {
                return i;
            }
            char c = data.charAt(i);
            if(c==')'){
                return (i + 1);
            }                
            if(c=='(') {
                int val = Integer.valueOf(data.substring(start, i));
                Node node = new Node(val);
                node.children = new ArrayList<>();
                children.add(node);
                start= deserialize(data, i+1, node.children);
                i=start;
                continue;
            }
            i++;
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));