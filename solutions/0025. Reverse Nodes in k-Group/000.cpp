/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
struct LNPointer
{
	ListNode * head;
	ListNode * tail;
	LNPointer() :head(NULL), tail(NULL)
	{}
};
struct NextGroup
{
	bool valid;
	ListNode * head;
	NextGroup() :valid(false), head(NULL)
	{}
};
class Solution {
	LNPointer reverseKGroupFixed(ListNode* head, int k) {
		LNPointer lp;
		ListNode * phead = head;
		ListNode * ptail = NULL;
		if (k == 1)
		{
			lp.head = head;
			lp.tail = head;
			return lp;
		}
		LNPointer lq = reverseKGroupFixed(head->next, k - 1);
		lq.tail->next = head;
		head->next = NULL;
		lp.head = lq.head;
		lp.tail = head;
		return lp;
	}
	NextGroup getNextGroup(ListNode * head, int k)
	{
		NextGroup ng;
		ListNode * p = head;
		for (int i = 0; i < k; i++)
		{
			if (p == NULL)
				return ng;
			p = p->next;
		}
		ng.head = p;
		ng.valid = true;
		return ng;
	}
	
public:
	ListNode* reverseKGroup(ListNode* head, int k) {
		NextGroup ng = getNextGroup(head, k);
		if (ng.valid)
		{
			LNPointer lp = reverseKGroupFixed(head,k);
			ListNode * nhead = reverseKGroup(ng.head,k);
			lp.tail->next = nhead;
			return lp.head;
		}
		return head;
	}
};