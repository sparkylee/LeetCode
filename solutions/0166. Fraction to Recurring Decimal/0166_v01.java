class Solution {
    long gcd(long numerator, long denominator) //numerator < denominator
    {
        long mod =   denominator%numerator;
        if(mod==0) return numerator;
        return gcd(mod,numerator);
    }
    public String fractionToDecimal(int numerator1, int denominator1) {
        long numeratorL = numerator1;
        long denominatorL = denominator1;
        boolean negative = ( numeratorL<0 && denominatorL>0 ) || ( numeratorL>0 && denominatorL<0 );
        numeratorL = Math.abs(numeratorL);
        denominatorL = Math.abs(denominatorL);

        Long intPart = numeratorL/denominatorL;
        numeratorL = numeratorL%denominatorL;
        if(numeratorL==0) return (negative?"-":"")+intPart.toString();
        long g = gcd(numeratorL,denominatorL);
        numeratorL = numeratorL/g;
        denominatorL = denominatorL/g;
        Map<Long, Integer> num2PosMap =  new HashMap<>();
        int i = 0;
        num2PosMap.put(numeratorL,i);
        List<Character> decPart = new ArrayList<>();
        while (true)
        {
            i++;
            numeratorL = numeratorL*10;
            long d = numeratorL/denominatorL;
            numeratorL = numeratorL%denominatorL;
            decPart.add((char)('0'+d));
            if(numeratorL ==0 || num2PosMap.containsKey(numeratorL))
                break;
            num2PosMap.put(numeratorL,i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(intPart.toString());
        sb.append('.');
        if(numeratorL==0)
        {
            for(char c: decPart) sb.append(c);
            return (negative?"-":"")+sb.toString();
        }
        int start= num2PosMap.get(numeratorL);
        for(int j=0;j<decPart.size();j++)
        {
            if(j==start)
                sb.append('(');
            sb.append(decPart.get(j));
        }
        sb.append(')');
        return (negative?"-":"")+sb.toString();
    }
}