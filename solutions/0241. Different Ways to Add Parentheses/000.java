class Solution {
    Map<Integer, List<Integer>> map;
    List<Integer> diffWaysToCompute( List<Integer> exprList, int start, int end) {
        List<Integer> results = new ArrayList<>();
        if(start>end || end >= exprList.size() || start < 0) {
            return results;       
        } 
        int index = (start << 5 | end);
        if(map.containsKey(index)) {
            return map.get(index);
        }
        if(start==end) {
            results.add(exprList.get(start));
            map.put(index, results);
            return results;
        }
        for(int i=start;i<=end;i++) {
            int val = exprList.get(i);
            if(val<0) {
                List<Integer> prevtList = diffWaysToCompute(exprList, start, i-1);
                List<Integer> nextList = diffWaysToCompute(exprList, i+1,end);
                for(int pv: prevtList) {
                    for(int nv: nextList) {
                        int value = 0;
                        switch (val) {
                            case -3:
                                value = pv*nv;
                                break;
                            case -2:
                                value = pv-nv;
                                break;
                            default:
                                value = pv+nv;
                                break;
                        }
                        results.add(value);
                    }
                }
            }
        }
        map.put(index, results);
        return results; 
    }
    public List<Integer> diffWaysToCompute(String expression) {     
        int value = 0;
        boolean valueFound = false;
        List<Integer> exprList = new ArrayList<>();
        for(int i=0;i<expression.length();i++){
            char c = expression.charAt(i);
            if(c>='0' && c<='9') {
                value *= 10;
                value += (c - '0');
                valueFound = true;
                continue;
            }
            if(valueFound) {
                exprList.add(value);
                valueFound = false;                
            }
            value = 0;
            int sign = -1;            
            if(c=='-') {             
               sign = -2;
            }
            if(c=='*') {             
               sign = -3;
            }
            exprList.add(sign);
        }
        if(valueFound) {
            exprList.add(value);
            valueFound = false;                
        }
        // System.out.println(exprList);
        this.map = new HashMap<>();
        List<Integer> results = diffWaysToCompute(exprList, 0, exprList.size()-1);
        return results;
    }
}