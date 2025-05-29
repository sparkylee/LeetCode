class Solution(object):
    def zzOrder(self,root,d):
        if not root:
            return;
        if d >= len(self.result):
            self.result.append([]);
        self.result[d].append(root.val);
        self.zzOrder(root.left ,d+1);
        self.zzOrder(root.right,d+1);
        return;
    def zigzagLevelOrder(self, root):
        """
        :type root: TreeNode
        :rtype: List[List[int]]
        """
        self.result = [];
        self.zzOrder(root,0);
        for i in range(len(self.result)):
            if i%2==1:
                self.result[i].reverse();
        return self.result;
        