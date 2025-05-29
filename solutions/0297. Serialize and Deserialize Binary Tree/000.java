/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    public class SerialNode {
            int val;
            int left;
            int right;
            SerialNode(TreeNode tn) {
                val = tn.val;
            left=-1;
            right=-1;
            }
            SerialNode(int val,int left,int right) {
                this.val = val;
                this.left=left;
                this.right=right;
            }

        }
        private int tree2Serial(TreeNode root, List<SerialNode> snList,int index)
        {
            if(root==null) return index;
            int index_left = -1, index_right = -1;
            if(root.left!=null) {
                index = tree2Serial(root.left, snList, index);
                index_left = index;
            }
            if(root.right!=null)
            {
                index = tree2Serial(root.right,snList,index);
                index_right = index;
            }
            snList.add(new SerialNode(root.val,index_left,index_right));
            return index+1;
        }
        public String serialize(TreeNode root) {
            List<SerialNode> snList = new ArrayList<>();
            tree2Serial(root,snList,-1);
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<snList.size();i++)
            {
                SerialNode sn = snList.get(i);
                if(i!=0) sb.append('|');
                sb.append(sn.val+","+sn.left +","+sn.right);
            }
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String [] strList = data.split("\\|");
            if(strList.length<1) return null;
            List<SerialNode> snList = new ArrayList<>();
            for (String str: strList)
            {
                String [] nodeInfo = str.split(",");
                if(nodeInfo.length!=3) return null;
                int val = Integer.valueOf(nodeInfo[0]);
                int left = Integer.valueOf(nodeInfo[1]);
                int right = Integer.valueOf(nodeInfo[2]);
                snList.add(new SerialNode(val,left,right));
            }
            List<TreeNode> tnList = new ArrayList<>();
            for(SerialNode sn: snList)
            {
                TreeNode tn = new TreeNode(sn.val);
                tnList.add(tn);
            }
            for(int i=0;i<tnList.size();i++)
            {
                TreeNode tn = tnList.get(i);
                SerialNode sn = snList.get(i);
                if(sn.left>=0)
                    tn.left = tnList.get(sn.left);
                if(sn.right>=0)
                tn.right = tnList.get(sn.right);
            }
            return tnList.get(tnList.size()-1);
        }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));