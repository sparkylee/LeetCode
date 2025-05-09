class Solution {
    public int evalRPN(String[] tokens) {
        List<Object> stack = new ArrayList<>();
        for(String str: tokens)
        {
            if(str.length()==1 )
            {
                char c = str.charAt(0);
                if(c=='+' || c=='-' || c=='*' ||  c=='/')
                {
                    int op_back =(Integer) stack.get(stack.size()-1);
                    stack.remove(stack.size()-1)  ;
                    int op_front =(Integer) stack.get(stack.size()-1);
                    stack.remove(stack.size()-1)  ;
                    int result = 0;
                    if(c=='+') result = op_front + op_back;
                    if(c=='-') result = op_front - op_back;
                    if(c=='*') result = op_front * op_back;
                    if(c=='/') result = op_front / op_back;
                    stack.add(result);
                    continue;
                }
            }
            Integer v = Integer.valueOf(str);
            stack.add(v);
        }
        return (int) stack.get(stack.size()-1);
    }
}