/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int [] known = new int[n];       
        for( int i=0;i<n;i++){
            known[i] = -1;            
        }
   
        int person=1;
        int candidate = 0;
        while(person<n) {
            boolean know = knows(candidate,person);
            if(know) {               
                candidate = person;                    
            }
            person ++;       
        }
        
        for( int i=0;i<n;i++){
            if(i!=candidate && (!knows(i, candidate) || knows(candidate,i))) {
                return -1;
            }    
        }
        return candidate;
    }
}