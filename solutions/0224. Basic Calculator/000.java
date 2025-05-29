class Solution {
      Stack stack = new Stack<>();
        private int seekNextNonSpace(String s,int i)
        {
            while(isSpace(s,i)) i++;
            return i;
        }

        private int [] readUnsignedInteger(String s,  int i)
        {
            int num = 0;
            while(isNumber(s,i))
            {
                num = num*10 + (s.charAt(i)-'0');
                i++;
            }
            return new int[]{i,num};
        }

        private boolean isNumber(String s, int i)
        {
            return i<s.length() && s.charAt(i)>='0' && s.charAt(i)<='9';
        }
        private boolean isSpace(String s, int i)
        {
            return i<s.length() && s.charAt(i)==' ';
        }

        private boolean popForParenthese()
        {
            int num = 0;
            while (true)
            {
                if(stack.isEmpty()) return false;
                Object o = stack.pop();
                if(o instanceof Integer)
                {
                    num += (int)o;
                    continue;
                }
                if(o instanceof Character)
                {
                    o=(Character)o;
                    if(o.equals('(')) {
                        if(stack.isEmpty()) return false;
                        Object sign = stack.pop();
                        if(sign instanceof Character && (sign.equals('+') || sign.equals('-')))
                        {
                            if(sign.equals('-')) num = -num;
                            stack.push(num);
                            return true;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
        }
        public int calculate(String s) {

            int i=0;

            while (i<s.length())
            {
                i=seekNextNonSpace(s,i);
                char sign = '+';
                if(i<s.length() && s.charAt(i)=='-')
                {
                    i++;
                    sign = '-';
                }
                else
                    if(i<s.length() && s.charAt(i)=='+')
                        i++;
                i=seekNextNonSpace(s,i);
                if(isNumber(s,i)) {
                    int[] result = new int[]{i, 0};
                    result = readUnsignedInteger(s, i);
                    int n =  result[1];
                    if(sign=='-') n=-n;
                    i = result[0];
                    stack.push(n);//integer need not signs
                    continue;
                }
                if(i<s.length() && s.charAt(i)=='(')
                {
                    stack.push(sign);
                    stack.push('(');
                    i++;
                    continue;
                }
                if(i<s.length() && s.charAt(i)==')')
                {
                    if(!popForParenthese())
                        return 0;
                    i++;
                    continue;
                }
            }
            int num = 0;
            while (!stack.isEmpty()) {
                Object o = stack.pop();
                if (o instanceof Integer) {
                    num += (int) o;
                }
            }
            return num;
        }
}