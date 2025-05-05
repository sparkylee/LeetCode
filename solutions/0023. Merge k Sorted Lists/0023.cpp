
class Solution {
	ListNode * mergeTwoLists(ListNode * phead, ListNode * rhead)
	{
		ListNode * p = phead;
		ListNode * r = rhead;
		if (phead == rhead)
		{
//			cout << "error" << endl;
//			exit(0);
		}

		if (r == NULL)
		{
			return phead;
		}
		if (p == NULL)
		{
			return rhead;
		}

		if (p->val > r->val)
		{
			ListNode * tmp = r;
			r = r->next;
			tmp->next = p;
			p = tmp;
			phead = p;
		}
		ListNode * q = p->next;
		while (q != NULL && r != NULL)
		{
			if (q->val <= r->val)
			{
				p = p->next;
				q = q->next;
			}
			else
			{
				ListNode * tmp = r;
				r = r->next;
				tmp->next = q;
				p->next = tmp;
				p = tmp;
			}

		}
		if (q == NULL)
		{
			p->next = r;
		}
		return phead;

	}
	ListNode* mergeKlistsAdv(vector<ListNode*>& lists, int lsize)
	{
		if (lsize == 0)
		{
			return NULL;
		}
		if (lsize == 1)
		{
			return lists[0];
		}
		lists[lsize - 2] = mergeTwoLists(lists[lsize - 2], lists[lsize - 1]);
		return mergeKlistsAdv(lists, lsize - 1);
	}
	ListNode* mergeKlistsAdv1(vector<ListNode*> & lists,int start, int end)
	{
		int lsize = end - start + 1;
		if (lsize == 0)
		{
			return NULL;
		}
		if (lsize == 1)
		{
			return lists[start];
		}
		ListNode * l1;
		ListNode * l2;
		if (lsize == 2)
		{
			l1 = lists[start];
			l2 = lists[end];
		}
		else
		{
			int hsize = lsize / 2;
			l1 = mergeKlistsAdv1(lists, start, start + hsize - 1);
			l2 = mergeKlistsAdv1(lists, start + hsize, end);
		}
		return mergeTwoLists(l1, l2);

	}
public:
	ListNode* mergeKLists(vector<ListNode*>& lists) {
		//return mergeKlistsAdv(lists, lists.size());

		int lsize = lists.size();
		return mergeKlistsAdv1(lists, 0, lsize - 1);
	}
	vector<int> mergeKlistsVector(vector<vector<int>> vs)
	{
		int size = 0;
		for (int i = 0; i < vs.size(); i++)
			size += vs[i].size();
		vector<int> vsm;
		vsm.reserve(size);
		for (int i = 0; i < vs.size(); i++)
		{
			vsm.insert(vsm.end(), vs[i].begin(), vs[i].end());
		}
		std:sort(vsm.begin(), vsm.end());
		return vsm;
	}
};