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

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null) 
            return "";
        String str = Integer.toString(root.val);
        String  leftStr =  serialize(root.left);
        if(!leftStr.isEmpty())
        {
            str +=" "+leftStr;
        }            
        String  rightStr =  serialize(root.right);
        if(!rightStr.isEmpty()){
            str += " "+rightStr;
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data==null || data.isEmpty() || data.isBlank()) {
            return null;
        }
        // System.out.println("data="+data);
        String [] strArray = data.split(" ");
        if(strArray.length==0){
            return null;
        }
        // System.out.println("length="+strArray.length);
        int [] arr = new int[strArray.length];
        for(int i=0;i<arr.length;i++){
            arr[i] = Integer.parseInt(strArray[i]);
        }
        return preOrderTree(arr,0, arr.length-1);
    }
    TreeNode preOrderTree(int [] arr, int start, int end) {
        if(start>end)
            return null;
        int k=start;
        int root_val = arr[start];
        while(k<=end && arr[k]<= root_val){
            k++;
        }
        TreeNode leftNode = preOrderTree(arr,start+1,k-1);
        TreeNode rightNode = preOrderTree(arr,k,end);
        TreeNode root = new TreeNode(root_val);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;