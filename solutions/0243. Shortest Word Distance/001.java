class Solution {
       public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int [] arr = new int[wordsDict.length];
        for(int i =0; i< wordsDict.length; i++) {
            if(wordsDict[i].equals(word1)) {
                arr[i] = 1;
            } else if (wordsDict[i].equals(word2)) {
                arr[i] = 2;
            }
        }
        int dis = arr.length;
        int last = -1;
        for(int i =0; i< arr.length; i++) {
           if(arr[i] == 0 )
                continue;
           if(last!= -1 && arr[i]!=arr[last]) {
               dis = Math.min(dis, (i - last));
           }
           last = i;
        }
        return dis;
    }
}