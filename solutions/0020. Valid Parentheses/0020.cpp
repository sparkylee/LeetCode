class Solution {
public:
    bool isValid(string s) {
    std::stack<char> bracket;
    for(int i = 0; i< s.length(); i++)
      {
        if(s[i]=='[' or s[i]=='(' or s[i]=='{')
          bracket.push(s[i]);
        else
          {
            if(bracket.empty())
              return false;
            if((s[i]==']' and bracket.top() == '[') or (s[i]==')' and bracket.top() == '(') or (s[i]=='}' and bracket.top() == '{'))
              {
                bracket.pop();
                continue;
              }
            else
              return false;
          }

      }
    if(bracket.empty())
      return true;
    else
      return false;

    }
};