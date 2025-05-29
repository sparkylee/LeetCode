class Solution {
    public int[][] reconstructQueue(int[][] people) {
        int [] buffer = new int[2];
        for(int start=0;start<people.length;start++) {            
            int [] person_max_height = people[start];
            for(int i=start;i<people.length;i++) {
                int [] person = people[i];
                if(person[0]>person_max_height[0] || (person[0]==person_max_height[0] && person[1]<person_max_height[1])) {
                    person_max_height = person;
                }
            }
            buffer[0] = person_max_height[0];
            buffer[1] = person_max_height[1];
            person_max_height[0] = people[start][0];
            person_max_height[1] = people[start][1];
            int index = buffer[1];
            for(int j=start;j>index;j--){
                people[j][0] = people[j-1][0];
                people[j][1] = people[j-1][1];
            }
            people[index][0] = buffer[0];
            people[index][1] = buffer[1];
        }
        return people;
    }
}