class Solution {
    public void addOperators(List<String> results, String num,int i, int target,long preValue,char op,long sum,char [] sb,int len) {

            if(i==num.length())
            {
                sum +=preValue;
                if(sum==target)
                    results.add(new String(sb,0,len));
                return;
            }
            int ending = Math.min(i+18,num.length());
            if(num.charAt(i)=='0') ending = i+1;
            long curValue = 0;
            for(int j=i;j<ending;j++) {
                curValue = curValue * 10 + (num.charAt(j) - '0');
                sb[len]=num.charAt(j);
                len++;
                if(op=='+')
                {
                    if(j==num.length()-1)
                    {
                        sum = sum+curValue;
                        if(sum==target)
                            results.add(new String(sb,0,len));
                        return;
                    }
                    sb[len]='+';
                    addOperators(results,num,j+1,target,0,'+',sum+curValue,sb,len+1);

                    sb[len]='-';
                    addOperators(results,num,j+1,target,0,'-',sum+curValue,sb,len+1);

                    sb[len]='*';
                    addOperators(results,num,j+1,target,curValue,'*',sum,sb,len+1);
                }
                if(op=='-')
                {
                    if(j==num.length()-1)
                    {
                        sum = sum-curValue;
                        if(sum==target)
                            results.add(new String(sb,0,len));
                        return;
                    }
                    sb[len]='+';
                    addOperators(results,num,j+1,target,0,'+',sum-curValue,sb,len+1);

                    sb[len]='-';
                    addOperators(results,num,j+1,target,0,'-',sum-curValue,sb,len+1);

                    sb[len]='*';
                    addOperators(results,num,j+1,target,-curValue,'*',sum,sb,len+1);
                }
                if(op=='*')
                {
                    if(j==num.length()-1)
                    {
                        sum=sum+preValue*curValue;
                        if(sum==target)
                            results.add(new String(sb,0,len));
                        return;
                    }
                    sb[len]='+';
                    addOperators(results,num,j+1,target,0,'+',sum+preValue*curValue,sb,len+1);

                    sb[len]='-';
                    addOperators(results,num,j+1,target,0,'-',sum+preValue*curValue,sb,len+1);

                    sb[len]='*';
                    addOperators(results,num,j+1,target,preValue*curValue,'*',sum,sb,len+1);
                }
            }

        }
        public List<String> addOperators(String num, int target) {
            List<String> results= new ArrayList<>();
            char [] sb = new char[num.length()*2];
            addOperators(results, num,0, target,0,'+',0,sb,0);
            return results;
        }
}