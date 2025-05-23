/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    bool hasPathSum(TreeNode* root, int sum) {
        if(root == NULL)
            return false;
        if(root->left == NULL and root->right == NULL)
        {
            if(root->val == sum) {return true;}
            else {return false;}
        }
        return hasPathSum(root->left,sum-root->val) or hasPathSum(root->right,sum-root->val);
    }
};