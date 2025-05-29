# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def inorderSuccessor(self, root: TreeNode, p: TreeNode) -> Optional[TreeNode]:
        curr = root
        successor = None
        target_found = False
        target = p.val
        while curr:
            if not curr.left:
                if target_found:
                    return curr
                if curr.val == target:
                    target_found = True
                curr = curr.right
            else:
            
                # Find the inorder predecessor of curr
                pre = curr.left
                while pre.right and pre.right != curr:
                    pre = pre.right

                if not pre.right:
                
                    # Make curr the right child of 
                    # its inorder predecessor
                    pre.right = curr
                    curr = curr.left
                else:
                
                    # Revert the changes made in the 'if' 
                    # part to restore the original tree
                    pre.right = None
                    if target_found:
                        return curr
                    if curr.val == target:
                        target_found = True
                    curr = curr.right

        # If no successor
        return None    