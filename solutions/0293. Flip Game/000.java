

class Solution {
    public List<String> generatePossibleNextMoves(String currentState) {
        List<String> results = new ArrayList();
        int strLen = currentState.length();
        for(int i = 0; i < strLen-1; i++)
        {  
            int j = i + 1;
            char c = currentState.charAt(i);
            char d = currentState.charAt(j);
            if (c=='+' && d== '+') {
                String frontPart = i > 0 ?  currentState.substring(0,i) : "";
                String backPart  = (j < strLen - 1) ? currentState.substring(j+1) : "";
                String newState = frontPart + "--" + backPart;
                results.add(newState);
            }
        }
        return results;
    }
}