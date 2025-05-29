class Solution {
    class Node {
       // (, ), type O:  merged 
        public char type;
        public int count;
        Node(char type, int count){
            this.type=type;
            this.count=count;
        }
    }
    Stack<Node> stack;
    void pushNode(Node node){
        if(stack.empty()) {
            stack.push(node);
            return;
        }
        Node top = stack.peek();
        if(node==null) {
            return;
        }
        if( top == null || node.type == '('
         || (top.type  == ')' && node.type == ')' ) ){
            stack.push(node);
            return;
        }
        if(node.type == ')')
        {
            if(top.type == 'O') {
                if(stack.empty()) {
                   stack.push(top);
                   return;
               }
               top = stack.pop();
               if(stack.empty()){
                   stack.push(top);
                   stack.push(node);
                   return;
               }
               Node second = stack.peek();
               if(second != null && second.type == '(') {
                   top.count += 2;
                   stack.pop();
                   pushNode(top);
                   return;
               } else {
                   stack.push(top);
                   stack.push(node);
                   return;
               }
            } else {
                Node node_O = new Node('O',2);
                stack.pop();
                pushNode(node_O);
                return;
            }
        }
        if(node.type == 'O') {
            if(top.type == 'O') {
                top.count += node.count;
                top = stack.pop();
                pushNode(top);
                return;
            } else {
                stack.push(node);
                return;
            }
        }
    }
    public int longestValidParentheses(String s) {
        stack = new Stack();
        for(int i=0;i<s.length();i++)
        {
            char c = s.charAt(i);
            Node node = new Node(c, 1);
            pushNode(node);
        }
        int m = 0;
        while(stack.size() > 0) {
            Node node = stack.pop();
            if (node.type == 'O' && node.count > m ) {
                m = node.count;
            }
        }
        return m;
    }
}