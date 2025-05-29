
import java.util.StringJoiner;class Solution {
    // String [] d2s;
    void generateAbbreviations(String word, int start,final boolean allowAbbr, final boolean allowPlain, List<String> sb, List<String> results) {
        if(start>=word.length()) {
            String str = String.join("",sb);
            results.add(str);
            return;
        }
        for(int i=start + 1;i<=word.length();i++){
              int abbrLen = i - start;  
              int index = abbrLen - 1;
              String str = word.substring(start, i);
            //   String strAbbr = d2s[index];
              boolean allowAbbrNext = false, allowPlainNext = false;
            //   if(strAbbr!=null){
            //     if(strAbbr.equals(str)) {
            //         if(allowAbbr) {
            //             sb.add(Integer.toString(abbrLen));    
            //             allowAbbrNext = false;
            //             allowPlainNext = true;                                 
            //             generateAbbreviations(word,i,allowAbbrNext, allowPlainNext, sb, results);
            //             sb.removeLast();       
            //         }
                    
            //     } else {
            //         if(allowPlain){
            //             sb.add(str) ;
            //             allowAbbrNext = true;
            //             allowPlainNext = false;  
            //             generateAbbreviations(word,i,allowAbbrNext, allowPlainNext, sb, results);
            //             sb.removeLast();       
            //         }                  
            //     }
               
            //   }  else {
                 if(allowPlain) {
                    sb.add(str) ;
                    allowAbbrNext = true;
                    allowPlainNext = false;  
                    generateAbbreviations(word,i,allowAbbrNext, allowPlainNext, sb, results);       
                    sb.removeLast();
                 }
                 if(allowAbbr){
                    sb.add(Integer.toString(abbrLen));
                    // d2s[index] = str;
                    allowAbbrNext = false;
                    allowPlainNext = true;  
                    generateAbbreviations(word,i,allowAbbrNext, allowPlainNext  , sb, results);       
                    sb.removeLast();
                    // d2s[index] = null;
                 }
            //   }
        }
    }
    public List<String> generateAbbreviations(String word) {
        List<String> results = new ArrayList<>();
        // d2s = new String[word.length()];
        List<String> sb = new ArrayList<>();
        generateAbbreviations(word,0,true,true,sb,results);        
        return results;
    }
}