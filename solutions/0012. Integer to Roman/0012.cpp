//Symbol 	I 	V 	X 	L 	C 	D 	M
//Value 	1 	5 	10 	50 	100 	500 	1,000

//I, II, III, IV, V, VI, VII, VIII, IX, X.

//Number 	4 	9 	40 	90 	400 	900
//Notation 	IV 	IX 	XL 	XC 	CD 	CM

class Solution {
    void addRoman(string & roman, int & num, int base, string letters)
    {
        int count = 0;
        count = num / base;
		for(int i = 0; i < count; i++)
			roman = roman + letters;
		num = num % base;
        return ;
    }
public:
    string intToRoman(int num) {
        string roman = "";
        addRoman(roman,num, 1000,"M");
        addRoman(roman,num, 900,"CM");
        addRoman(roman,num, 500,"D");
        addRoman(roman,num, 400,"CD");
        addRoman(roman,num, 100,"C");
        addRoman(roman,num, 90,"XC");
        addRoman(roman,num, 50,"L");
        addRoman(roman,num, 40,"XL");
        addRoman(roman,num, 10,"X");
        addRoman(roman,num, 9,"IX");
        addRoman(roman,num, 5,"V");
        addRoman(roman,num, 4,"IV");
        addRoman(roman,num, 1,"I");
        return roman;

    }
};