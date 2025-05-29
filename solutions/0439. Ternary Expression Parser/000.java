class Solution {
    public String parseTernary(String expression) {
        Stack<Character> stack = new Stack<>();        
        for(int i=expression.length()-1;i>=0;i--) {
            char c = expression.charAt(i);
            if(c==':')
                continue;
            if((c=='T' || c=='F') && !stack.isEmpty() && stack.peek()=='?') {                
                stack.pop();
                Character true_v = stack.pop();
                if(c=='T'){
                    stack.pop();
                    stack.add(true_v);
                }
                continue;                
            }
            stack.add(c);
        }
        return stack.peek() +"";
    }
}