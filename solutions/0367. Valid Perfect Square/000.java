class Solution {
   public boolean isPerfectSquare(int num) {
            return isPerfectSquareRecursive(num,1,num);
        }
        private boolean isPerfectSquareRecursive(int num,int min,int max) {
            if(min >= max)
                return min*min==num;
            long middle = (max+min)/2;
            long middle_product = middle*middle;
            if(middle_product>num)
                return isPerfectSquareRecursive(num,min,(int)middle);
            else if (middle_product == num)
                return true;
            else
                return isPerfectSquareRecursive(num,(int)middle+1,max);
        }
}