class Solution {
    public static Integer getInteger(String s) {
        Integer x = null;
        try {
            x = Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
        // only got here if we didn't return false
        return x;
    }
    public int calPoints(String[] ops) {
        List<Integer> stack = new ArrayList<>();
        for(int i=0;i<ops.length;i++)
        {
            String s = ops[i];
            if(s==null) continue;
            Integer x = null;
            if(( x = getInteger(s))!=null)
            {
                stack.add(x);
                continue;
            }
            if(s.equals("+")) {
                if(stack.size()>=2)
                {
                    Integer y = stack.get(stack.size() - 1) + stack.get(stack.size() - 2);
                    stack.add(y);
                }
                continue;
            }
            if(s.equals("D")) {
                if(stack.size()>=1)
                {
                    Integer y = stack.get(stack.size() - 1) * 2;
                    stack.add(y);
                }
                continue;
            }
            if(s.equals("C")) {
                if(stack.size()>=1) {
                    stack.remove(stack.size()-1);
                }
            }
        }
        int sum = 0;
        for(Integer x : stack)
            sum += x;
        return sum;
    }
}