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
    int minDepth(TreeNode* root) {
       if (root == NULL) 
        return 0;
        int lv = 0, rv = 0;
        lv  = minDepth(root->left);
        rv  = minDepth(root->right);
        if(lv == 0 or rv == 0)
            return ((lv>=rv)?lv:rv)+1;
        else
            return ((lv<=rv)?lv:rv)+1;
        
    }
};