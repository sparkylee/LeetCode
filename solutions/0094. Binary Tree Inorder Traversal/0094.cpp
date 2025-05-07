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
    vector<int> inorderTraversal(TreeNode* root) {
        vector<TreeNode*> tstack;
		vector<int> ve;
		TreeNode * p = root;
		tstack.push_back(p);
		while (p != NULL)
		{
			if (p->left != NULL)
			{
				tstack.push_back(p->left);
				p = p->left;
				continue;

			}
			else
			{
				ve.push_back(p->val);
				tstack.pop_back();
			}

			while (p->right == NULL)
			{
				if (tstack.empty())
					return ve;
				p = tstack.back();
				ve.push_back(p->val);
				tstack.pop_back();
			}
			tstack.push_back(p->right);
			p = p->right;
		}
		return ve;
    }
};