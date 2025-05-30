/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    boolean isInit = true;
    int sum = 0;
    public int rand10() {
        if(isInit) {
            for(int i =0; i < 9; i++) {
                sum += rand7();
            }
            isInit = false;
        }
        sum += rand7();
        return (sum % 10) + 1;
    }
}