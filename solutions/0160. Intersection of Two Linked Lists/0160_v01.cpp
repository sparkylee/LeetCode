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
    ListNode *getIntersectionNode(ListNode *headA, ListNode *headB) {
        ListNode * pheadA = headA;
		ListNode * pheadB = headB;
		int A = 0, B = 0;
		while (pheadA != NULL)
		{
			A++;
			pheadA = pheadA->next;
		}
		while (pheadB != NULL)
		{
			B++;
			pheadB = pheadB->next;
		}
		int C = (A >= B) ? (A - B) : (B - A);
		ListNode * phead = (A >= B) ? headA : headB;
		for (int i = 0; i < C; i++)
		{
			phead = phead->next;
		}
		pheadA = (A>=B) ? phead : headA;
		pheadB = (A>=B) ? headB : phead;
		while (pheadA != pheadB)
		{
			pheadA = pheadA->next;
			pheadB = pheadB->next;
		}
		return pheadA;
    }
};