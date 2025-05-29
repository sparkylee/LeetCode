class Solution {
      int nc =0;
        private int incrUntlLetter(String s,int i)
        {
            while(i<s.length() && ((s.charAt(i)==' ') || (s.charAt(i))=='\t'))
            {
                i++;
            }
            return i;
        }

        private int checkNumbers(String s,int i)
        {
            while(i<s.length() && ((s.charAt(i)<='9') && (s.charAt(i))>='0'))
            {
                i++;
                this.nc++;
            }
            return i;
        }
        private int checkUnsignedDecimalNumbers(String s,int i)
        {
            i=checkNumbers(s,i);
            if(i<s.length() && s.charAt(i)=='.')  i++;
            i=checkNumbers(s,i);
            return i;
        }
        private int checkSignedDecimalNumbers(String s,int i)
        {
            if(i<s.length() &&  (s.charAt(i)=='+' || s.charAt(i)=='-')) i++;
            return  checkUnsignedDecimalNumbers(s,i);
        }
        private int checkSignedInteger(String s,int i)
        {
            if(i<s.length() && (s.charAt(i)=='+' || s.charAt(i)=='-')) i++;
            return checkNumbers(s,i);
        }

        public boolean isNumber(String s) {
            this.nc = 0;
            int i = incrUntlLetter(s,0);
            i= checkSignedDecimalNumbers(s,i);
            if(this.nc==0) return false;
            if(i<s.length() && ( s.charAt(i)=='e' || s.charAt(i)=='E'))
            {
                this.nc = 0;
                i=checkSignedInteger(s,i+1);
                if(this.nc==0) return false;
            }

            i = incrUntlLetter(s,i);
            return i>=s.length();
        }
}