class Solution {
      private long calcDigitCount(int dc) //dc digit count
        {
            long ceiling = 1;//exclusive ceiling
            for(int i =0; i< dc;i++)
            {
                ceiling = ceiling * 10;
            }
            long bottom = ceiling/10;//inclusive bottome
            return (ceiling-bottom)*dc;
        }
        private long calcCeilingCount(int dc,long prevCnt)
        {
            return  prevCnt+calcDigitCount(dc);
        }
        public int findNthDigit(int n) {
            int i =1;
            if(n<10) return n;
            long prevCnt = 9;
            int dc = 2,prevDc = 1;
            long ceilingCnt = calcCeilingCount(dc,prevCnt);
            while(ceilingCnt < n)
            {
                prevCnt = ceilingCnt;
                prevDc = dc;
                dc = dc + 1;
                ceilingCnt = calcCeilingCount(dc,prevCnt);
            }

            long leftoverCnt = n - prevCnt -1;
            long ith =   (leftoverCnt)/dc;//start from 0
            long jth = dc - (leftoverCnt%dc)%dc -1;//start from 0
            long ceilingValue = 1;//exclusive ceiling value
            for(int k =0; k< dc;k++)
            {
                ceilingValue = ceilingValue * 10;//exclusive ceiling value
            }
            long value  = ceilingValue/10  + ith;
            long v = value;
            for(int j = 0; j< jth;j++)
            {
                v = v/ 10;
            }
            return (int)v%10;

        }
}