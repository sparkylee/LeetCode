#include <iostream>
#include <string>
#include <vector>
using namespace std;

class Solution {
    int k = 0;
    void pushStrVe(vector<string> & d2L,  vector<string> & strVe, string str,int index)
    {
     // cout<<str<<endl;
      
      if(index >= str.length())
      {
      //  cout<<str<<endl;
        strVe[k] =str;
        k++;
        return;
      }
      int ccode = str[index] - '2';
      //cout<<ccode<<endl;
      string letters = d2L[ccode];
      for(int i = 0; i < letters.length();i++)
      {
        str[index] = letters[i];
        pushStrVe(d2L,strVe,str,index+1);
      }
      return;
    }
public:
    vector<string> letterCombinations(string digits) {
          vector<string> d2L;
          d2L.push_back("abc"); //2
          d2L.push_back("def"); //3
          d2L.push_back("ghi"); //4
          d2L.push_back("jkl");
          d2L.push_back("mno");
          d2L.push_back("pqrs");
          d2L.push_back("tuv"); //8
          d2L.push_back("wxyz");//9
          
          int len = digits.length();
          if(len <=0)
             {
                vector<string> ve1;      
                return ve1;
              }
          
          int comCnt = 1;
          for(int i = 0; i < len; i ++)
          {
            int ccode = digits[i] - '2';
            if(ccode < 0 || ccode >7)
              {
                vector<string> ve1;      
                return ve1;
              }
            comCnt = comCnt * d2L[ccode].length();
          }
      //    cout<<comCnt<<endl;
          //cout<<str<<endl;
          std::vector<string> strVe (comCnt,"");    
          k = 0;
          pushStrVe(d2L,strVe,digits,0);
          return strVe;
    }
    void printStrVe(vector<string> strVe)
    {
      for(int i =0; i < strVe.size(); i++)
      {
        cout<<strVe[i]<<endl;
      }
    }
};