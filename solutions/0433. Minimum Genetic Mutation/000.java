class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> bset = new HashSet<>();
        for(String bstr : bank) {
            bset.add(bstr);
        }
        int count = 0;
        char [] letters = {'A','C','G','T'};
        Set<String> explored = new HashSet<>();
        explored.add(startGene);
        Set<String> current = new HashSet<>();
        current.add(startGene);
        while (true) {
            Set<String> next_set = new HashSet<>();
            for(String str: current) {
                if(str.equals(endGene))
                    return count;
                for(int i=0;i<str.length();i++){
                    char c = str.charAt(i);
                    for(int j=0;j<letters.length;j++) {
                        char d = letters[j];
                        if(c!=d){
                            String str_new = str.substring(0,i)+d+str.substring(i+1);  
                            // System.out.println("checking "+str_new);
                            if(bset.contains(str_new) && !explored.contains(str_new)) {
                                next_set.add(str_new);
                                explored.add(str_new);
                            }
                        }
                    }
                }               
            }
            if(next_set.isEmpty()) {
                return -1;
            }                
            current = next_set;    
            count++;           
        } 
    }
}