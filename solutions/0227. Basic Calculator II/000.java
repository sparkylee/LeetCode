class Solution {
     int index=0;
        private int readUnsignedInteger(String s)
        {
            int value = 0;
            while (true) {
                readSpaces(s);
                if(this.index<s.length() && s.charAt(this.index)>='0' && s.charAt(this.index)<='9') {
                    value = value * 10 + s.charAt(this.index) - '0';
                    this.index++;
                }
                else
                    return value;
            }
        }
        private void readSpaces(String s)
        {
            while (this.index<s.length() && s.charAt(this.index)==' ')
                this.index++;
        }
        private int readSign(String s)
        {
            readSpaces(s);
            if(this.index<s.length() && s.charAt(this.index)=='-' ) {
                this.index++;
                return -1;
            }
            if(this.index<s.length() && s.charAt(this.index)=='+' ) {
                this.index++;
                return 1;
            }
            return 1;
        }
        private boolean readMultiplier(String s)
        {
            readSpaces(s);
            if (this.index<s.length() && s.charAt(this.index)=='*' )
            {
                this.index ++;
                return true;
            }
            return false;
        }
        private boolean readDivider(String s)
        {
            readSpaces(s);
            if (this.index<s.length() && s.charAt(this.index)=='/' )
            {
                this.index ++;
                return true;
            }
            return false;
        }
        //////////////////
        private int readSignedInteger(String s)
        {
            int sign = readSign(s);
            int value = readUnsignedInteger(s);
            return sign*value;
        }

        private int readExpression(String s)
        {
            int op_front = readSignedInteger(s);
            while (true) {
                if (readMultiplier(s)) {

                    int op_back = readSignedInteger(s);
                    op_front = op_front * op_back;
                } else {
                    if (readDivider(s)) {
                        int op_back = readSignedInteger(s);
                        op_front = op_front / op_back;
                    }
                    else break;
                }
            }
            return op_front;

        }
        public int calculate(String s) {
            int sum = 0;
            while (this.index<s.length())
            {
                sum+=readExpression(s);
                readSpaces(s);
            }
            return sum;
        }
}