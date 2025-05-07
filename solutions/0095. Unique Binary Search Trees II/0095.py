class Solution(object):
    def copyTree(self,head):
        if not head:
            return None;
        newHead = TreeNode(head.val);
        newHead.left  = self.copyTree(head.left);
        newHead.right = self.copyTree(head.right);
        return newHead;
    def genTrees(self,i,j):
        TL = []
        if i > j:
            return TL;
        for k in range(i,j+1):
            head = TreeNode(self.NL[k]);
            TL_left = self.genTrees(i,k-1);
            if not TL_left:
                TL_left.append(None);
            TL_right = self.genTrees(k+1,j);
            if not TL_right:
                TL_right.append(None);
            for item_left in TL_left:
                for item_right in TL_right:
                    head.left  = item_left;
                    head.right = item_right;
                    TL.append(self.copyTree(head));
        return TL;

    def generateTrees(self, n):
        """
        :type n: int
        :rtype: List[TreeNode]
        """
        self.n = n;
        self.NL = list(range(1,n+1));
        result = self.genTrees(0,n-1);
        return result;