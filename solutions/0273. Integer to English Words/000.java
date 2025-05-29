class Solution {
  String [] Digits = {"Zero","One","Two","Three","Four","Five","Six","Seven","Eight",
        "Nine","Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen",
        "Eighteen","Nineteen"};
        String [] Tens  = {"Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
        String hundred  = "Hundred";
        String thousand = "Thousand";
        String million  = "Million";
        String billion  = "Billion";
        private void numberToWords1000(int num, StringBuilder sb)
        {
            if(num==0) return;
            int div = num/100;
            if(div>0)
            {
                sb.append(Digits[div]).append(' ');
                sb.append(hundred).append(' ');
            }
            int residue = num%100;
            numberToWords100(residue,sb);
        }
        private void numberToWords100(int num, StringBuilder sb)
        {
            if(num==0) return;
            if(num>=20)
            {
                int index = (num - 20)/10;
                sb.append(Tens[index]).append(' ');
                num =num- index*10-20;
            }
            numberToWords20(num,sb);
        }
        private void numberToWords20(int num, StringBuilder sb)
        {
            if(num==0) return;
            if(num<20)
            {
                sb.append(Digits[num]).append(' ');
                return;
            }
        }
        public String numberToWords(int num) {
            if(num<20) return Digits[num];
            StringBuilder sb = new StringBuilder();
            int div = 0, residue = 0;

            div = num/1000000000;

            if(div>=1)
            {
                numberToWords1000(div,sb);
                sb.append(billion).append(' ');
                num =  num%1000000000;;
            }
            div = num/1000000;
            if(div>=1)
            {
                numberToWords1000(div,sb);
                sb.append(million).append(' ');
                num =  num%1000000;;
            }

            div = num/1000;
            if(div>=1)
            {
                numberToWords1000(div,sb);
                sb.append(thousand).append(' ');
                num =  num%1000;;
            }

            numberToWords1000(num,sb);
                if(sb.charAt(sb.length()-1)==' ')
                sb.deleteCharAt(sb.length()-1);
            return  sb.toString();
        }
}