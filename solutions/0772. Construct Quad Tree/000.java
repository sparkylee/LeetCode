/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
*/
class Solution {
    public Node construct(int[][] grid) {
        return constructNodeRecursive(grid,0,grid.length-1,0,grid.length-1);
    }
    private Node constructNodeRecursive(int[][] grid, int x_start, int x_end, int y_start, int y_end)
    {
        int x_len = x_end - x_start + 1, y_len= y_end - y_start + 1;
        if(x_len <=0 || y_len <= 0 || x_len != y_len) return null;
        int len  = x_len;
        if(len== 1)
            return new Node(grid[y_start][x_start]>0,true,null,null,null,null);
        Node nodeTL = constructNodeRecursive(grid,x_start,(x_end+x_start)/2,y_start,(y_end+y_start)/2);
        Node nodeTR = constructNodeRecursive(grid,(x_end+x_start)/2+1,x_end,y_start,(y_end+y_start)/2);
        Node nodeBL = constructNodeRecursive(grid,x_start,(x_end+x_start)/2,(y_end+y_start)/2+1,y_end);
        Node nodeBR = constructNodeRecursive(grid,(x_end+x_start)/2+1,x_end,(y_end+y_start)/2+1,y_end);
        if(nodeTL == null || nodeTR== null || nodeBL == null || nodeBR == null) return null;
        if(!nodeTL.isLeaf || !nodeTR.isLeaf || !nodeBL.isLeaf || !nodeBR.isLeaf
            || nodeTR.val!=nodeTL.val || nodeBL.val!=nodeTL.val || nodeBR.val !=nodeTL.val)
            return new Node(false,false,nodeTL,nodeTR,nodeBL,nodeBR);
        return new Node(nodeTL.val,true,null,null,null,null);
    }
}