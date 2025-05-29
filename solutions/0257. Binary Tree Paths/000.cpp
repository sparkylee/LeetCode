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
    vector<string> binaryTreePaths(TreeNode* root) {
        vector<string> v1;
        if(root == NULL)
        {
            return v1;
        }
        else
        {
            vector<string> v,vl,vr;    
            vl = binaryTreePaths(root->left);
            vr = binaryTreePaths(root->right);
            if (vl.size()==0 and vr.size()==0)
            {
                v.push_back(std::to_string(root->val));
                return v;
            }
            for(int i = 0; i < vl.size();i++)
            {
                vl[i] = std::to_string(root->val) +"->"+vl[i];
                v.push_back(vl[i]);
            }
            for(int i = 0; i < vr.size();i++)
            {
                vr[i] = std::to_string(root->val) +"->"+vr[i];
                v.push_back(vr[i]);
            }
          /*  v.reserve( vl.size() + vr.size() ); // preallocate memory
            if(vl.size() != 0)
            {
                v.insert( v.end(), vl.begin(), vl.end() );
            }
            if(vr.size() != 0)
            {
                v.insert( v.end(), vr.begin(), vr.end() );
            }
            */
            return v;
        }    
        return v1;     
    }
};