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
    bool isPalindrome(ListNode* head) {
         if(head==NULL or head->next == NULL)
      return true;    
    int count  = 0;
    ListNode * phead = head;
    while(phead!=NULL)
      {
	count++;
	phead = phead->next;
      }
    int hcount = count / 2;
    int even_odd = count % 2;   
    ListNode* ahead ;
    ListNode* bhead ;
    if(count<=1)
      return true;
    if(count == 2)
    {
      ahead = head;
      bhead = head->next;
    }
    else
      {
	ListNode* p1 = head;
	ListNode* p2 = p1->next;
	ListNode* p3 = p2->next;
	p1->next = NULL;
	for(int k = 1; k < hcount; k++)
	  {

	    p2->next = p1;

	    p1 = p2;
	    p2 = p3;
	    p3 = p3->next;
	  }
	ahead = p1;
	
	if(even_odd==1)
	  bhead = p3;
	else
	  bhead = p2;
      }
    while(ahead != NULL and bhead != NULL)
      {
	if(ahead->val != bhead->val)
	  return false;
	ahead = ahead->next;
	bhead = bhead->next;
      }

    return true;
    }
};