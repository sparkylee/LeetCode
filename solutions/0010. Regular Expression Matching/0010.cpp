class Solution {

public:
    bool isMatch(string s, string p) {
        if(s.empty() && p.empty())
            return true;
        if(!s.empty() && p.empty())
            return false;
        if(s.empty() && !p.empty())
        {
            if(p.length()>=2 && p[1] == '*')
                return isMatch(s,p.substr(2));
            else
                return false;
        }
        if(s[0] == p[0] || p[0] == '.')
        {
            if(p.length()>=2 && p[1] == '*')
                return isMatch(s.substr(1),p) || isMatch(s,p.substr(2));
            else
                return isMatch(s.substr(1),p.substr(1));
        }
        else
        {
            if(p.length()>=2 && p[1] == '*')
                return isMatch(s,p.substr(2));
            else
                return false;
        }
        return false;
    }
};