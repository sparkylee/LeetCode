class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> strList = new ArrayList<>();
        int i = 0;
        while(i<words.length)
        {
            StringBuilder sb = new StringBuilder(maxWidth);
            int start = i,end = i;
            int residue = maxWidth - words[end].length();
            int len = words[end].length();
            while(end+1<words.length && (1+words[end+1].length()<=residue))
            {
                end++;
                residue -= (words[end].length()+1);
                len += words[end].length();
            }
            int wordCountPerLine = end-start + 1;
            int spaceCount = (wordCountPerLine-1)+residue;
            i = start;
            sb.append(words[i]);
            boolean isLastLine = (end+1>=words.length);
            if(wordCountPerLine>1) {
                int div = isLastLine?1:spaceCount/(wordCountPerLine-1),
                        mod = isLastLine?0:spaceCount%(wordCountPerLine-1);
                int k = 0;
                for (i = start + 1; i <= end; i++) {
                    for(int j=0;j<div;j++) {
                        sb.append(' ');
                        spaceCount--;
                    }
                    if(k<mod)
                    {
                        sb.append(' ');
                        spaceCount--;
                        k++;
                    }
                    sb.append(words[i]);
                }
            }
            for(int j=0;j<spaceCount;j++)//only valid for a single-word line
                sb.append(' ');
            strList.add(sb.toString());
            i=end+1;
        }
        return strList;
    }
}