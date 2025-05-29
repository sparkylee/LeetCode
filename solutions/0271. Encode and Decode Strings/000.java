public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {        
        StringBuilder sb = new StringBuilder();
        sb.append(strs.size());
        sb.append(' ');
        for(int i=0;i<strs.size();i++) {
            sb.append(strs.get(i).length());
            sb.append(' ');
        }
        for(String s: strs)
            sb.append(s);
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        // System.out.println(s);
        int index = s.indexOf(' ', 0);
        int num_of_str = Integer.parseInt(s.substring(0, index));
        List<String> strs = new ArrayList<>();
        if(num_of_str<=0) {
            return strs;
        }
        int [] lens = new int[num_of_str];
        for(int i=0;i<num_of_str;i++) {
            int next_index = s.indexOf(' ', index+1);
            int len = Integer.parseInt(s.substring(index+1, next_index));  
            lens[i] = len;
            index = next_index;
        }    
        index++;    
        for(int i=0;i<num_of_str;i++) {
            strs.add(s.substring(index, index+lens[i]));            
            index = index+lens[i];
        }
        return strs;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));