class MinStack {
public:
  /** initialize your data structure here. */
	vector<int> mstack;
	int min_i;
	MinStack() {
		min_i = -1;
	}

	void push(int x) {
		mstack.push_back(x);
		if (min_i < 0)
		{
			min_i = 0;
			return;
		}
		if (x < mstack[min_i])
			min_i = mstack.size() - 1;
		return;
	}

	void pop() {
		if (mstack.empty())
			return;
		if (mstack.back() > mstack[min_i])
		{
			mstack.pop_back();
			return;
		}
		mstack.pop_back();
		if (mstack.empty())
		{
			min_i = -1;
			return;
		}

		min_i = 0;
		for (int i = 0; i < mstack.size() ; i++)
		{
			if (mstack[i] < mstack[min_i])
			{
				min_i = i;
			}
		}
		return;
	}

	int top() {
		return mstack.back();
	}

	int getMin() {
		return mstack[min_i];
	}
};

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */