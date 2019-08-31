package lc4xx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public  class lc420
{

    class Solution {
        private int missingLowerCase(String s) {
            return missingCharacter(s,'a','z');
        }

        private int missingUpperCase(String s) {
            return missingCharacter(s,'A','Z');
        }

        private int missingDigit(String s) {
            return missingCharacter(s,'0','9');
        }

        private int missingCharacter(String s, char start, char end) {
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i)>=start && s.charAt(i)<=end ) return 0;
            }
            return 1;
        }

        private int injectCharacter(String s) {
            int i = 0;
            int count = 0;
            while (i + 2 < s.length()) {
                if (s.charAt(i) == s.charAt(i + 1) && s.charAt(i) == s.charAt(i + 2)) {
                    count++;
                    i=i+2;
                } else
                    i++;
            }
            return count;
        }

        private int strongPasswordCheckerLess6(String s) {
            //condition 2
            int missCnt = missingLowerCase(s) + missingLowerCase(s) + missingDigit(s);
            //condition 3
            int injectCnt = injectCharacter(s);
            //condition 1
            int lessCnt = 6 - s.length();
            return Math.max(Math.max(missCnt,injectCnt),lessCnt) ;
        }

        private int strongPasswordCheckerLess6T20(String s) {
            return 0;
        }

        private int strongPasswordCheckerOver20(String s) {
            return 0;
        }

        public int strongPasswordChecker(String s) {
            if(s.length()<6) return strongPasswordCheckerLess6(s);
            if(s.length()>20) return strongPasswordCheckerOver20(s);
            return strongPasswordCheckerLess6T20(s);
        }
    }

}
