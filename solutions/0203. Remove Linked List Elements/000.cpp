/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* removeElements(ListNode* head, int val) {
    
    if(head==NULL)
      return head;
    ListNode * hnext = removeElements(head->next,val);
    if(head->val==val)
      return hnext;
    else
      {
        head->next = hnext;
        return head;
      }

    }
};