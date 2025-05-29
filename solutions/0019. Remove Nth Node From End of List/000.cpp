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
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        ListNode*  phead = new ListNode(-1);
        phead->next = head;
        ListNode * p1 = phead, *p2 = head;
        while(n>0 and p2 != NULL)
        {
            p2 = p2->next;
            n--;
        }
        while(p2!=NULL)
        {
            p2 = p2->next;
            p1 = p1->next;
        }
        p1->next = p1->next->next;
        return phead->next;
    }
};