/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    List<NestedInteger> nestedList;
    Stack<Iterator> stack;
    int next_value;
    boolean has_next;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        this.has_next = false;
        stack = new Stack<>();
        stack.add(this.nestedList.iterator());
        checkNext();
    }
    void checkNext() {
         while(true) {
            if(stack.empty()) {
                this.has_next = false;
                return;
            }
            Iterator iter = stack.peek();
            if(iter.hasNext()) {
                NestedInteger ni = (NestedInteger)iter.next();
                if(ni.isInteger()) {
                    this.next_value =  ni.getInteger();
                     this.has_next = true;
                    return;
                } else {
                    this.stack.add(ni.getList().iterator());
                    continue;
                }
            } else {
                stack.pop();
            }
        }       
    }
    @Override
    public Integer next() {
        if(this.has_next)
        {
            int ret_value = this.next_value;
            checkNext();
            return ret_value;
        }
        return null;
    }

    @Override
    public boolean hasNext() {       
       return this.has_next;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */