class Solution {
   
    public String removeDuplicateLetters(String s) {      
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);            
            map.put(c, i);
        }
        Set<Character> set = new HashSet<>();
        Stack<Character> stack =  new Stack<>();
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);          
            while (true) {
                if(stack.isEmpty())
                {
                    stack.add(c);
                    set.add(c);
                    break;
                }
                if(set.contains(c)) {
                    break;
                }                
                char c_peek = stack.peek();                             
                if(c_peek<c){
                    stack.add(c);
                    set.add(c);
                    break;
                }
                int c_peek_index = map.get(c_peek);
                if(c_peek_index<i){
                    stack.add(c);
                    set.add(c);
                    break;
                }
                stack.pop();
                set.remove(c_peek);
            }           
            
        }
        StringBuilder sb = new StringBuilder();
        stack.stream().forEach(c -> sb.append(c)); // iterates from bottom to top of stack      
        return sb.toString();
    }
}