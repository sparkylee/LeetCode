class Solution {
    const bool dout0 = false;
    const bool dout1 = false;
    const bool dout2 = false;
    const bool dout3 = false;
    bool isWordMatch(string & s, int loc, string word)
    {
        if(dout1)
        {
            cout<<"matching word: loc="<<loc<<" word="<<word<<endl;
        }
        if(loc+word.size() > s.size())
        {
            if(dout0)
                cout<<"line 15"<<endl;
            return false;
        }
        for(int i = 0; i < word.size();i++)
        {
            if(word[i]!='?' && s[loc+i] != word[i])
            {
                if(dout0)
                    cout<<"line 23\n";
                return false;
            }
        }
        return true;
    }
    bool isMatchIndex(string & s, int si, int s_end, vector<string> & v, int vi,int v_end)
    {

        if(dout0)
        {
            cout<<"wordMatched: si="<<si<<" s_end="<<s_end<<" vi="<<vi<<" v_end="<<v_end<< endl;
        }
        if(vi > v_end)
            return true;
        if( si > s_end )
        {
            if(dout0)
                cout<<"line 33 "<<endl;
            return false;
        }
        for(int i = si ; i <= s_end; i++)
        {
            if((i+v[vi].length() - 1 <= s_end) && isWordMatch(s,i,v[vi]))
            {
                bool matched = isMatchIndex(s,i+v[vi].size(),s_end,v,vi+1,v_end);
                return matched;
            }
        }
        return false;
    }
    vector<string> preprocessing(string p, bool & withStar)
    {
        vector<string>  ve;
        string str = "";
        for(int i =0; i < p.size(); i++)
        {
            if(  p[i]=='*'  )
            {
                withStar = true;
                if(!str.empty())
                {
                    if(dout2)
                    {
                        cout<<" i="<<i<<" str="<<str<<endl;
                    }
                    ve.push_back(str);
                    str = "";
                }
            }
            else
                str += p[i];
        }
        if(!str.empty())
            ve.push_back(str);
        return ve;
    }
    void printvector(vector<string> v, int v_start, int v_end)
    {
      int i = v_start;
      while(i>=v_start && i <= v_end && i < v.size())
      {
            cout<<v[i]<<" ";
            i++;
        }
        cout<<endl;
    }

    int matchHead(string & p,string & s, int & s_start, int & s_end, vector<string> & v, int & v_start,int & v_end)
    {

        int i = 0;
        while(i < p.size() && i < s.size() && p[i] != '*' )
        {
            if(p[i] != '?' && p[i] != s[i])
            {
                if(dout0)
                    cout<< " at line 86: i=" <<endl;
                return -1;
            }
            i++;
        }
        if(i < p.size()  && p[i] == '*' )
        {
            if(i>0)
                v_start ++;
            s_start = i;
            if(dout0)
                cout<<"line 95: v_start="<<v_start<< "  s_start=" <<s_start<<endl;
            return 0;
        }
        if(i == p.size() && i == s.size())
            return 1;
        return -1;

    }
    int matchTail(string & p,string & s, int & s_start, int & s_end, vector<string> & v, int & v_start,int & v_end)
    {
        int   i = 0;
        int pi = p.size() - i - 1;
        int si = s.size() - i - 1;
        while(pi >= 0 && si >= s_start && p[pi] != '*' )
        {

            if(p[pi] != '?' && p[pi] != s[si])
            {
                if(dout0)
                    cout<< "line 105: pi=" <<pi<<"  si="<<si<<endl;
                return -1;
            }
            i++;
            pi = p.size() - i - 1;
            si = s.size() - i - 1;
        }

        if(pi >= 0 && p[pi] == '*' )
        {
            if(i > 0)
                v_end --;
            s_end = si;
            return 0;
        }
        if(pi==-1 && si == -1)
            return 1;
        return -1;
    }
    void printstring(string s, int start ,int end)
    {
        int i = start;
        while(i>=0 && i <= end && i < s.size())
        {
            cout<<s[i];
            i++;
        }
        cout<<endl;
    }

public:
    bool isMatch(string s, string p) {
        bool withStar = false;
        vector<string> ve = preprocessing(p,withStar);
        int s_start = 0, s_end = s.size() -1, v_start = 0, v_end = ve.size()-1;

        if(dout2)
        {
            cout<<"After preprocessing the pattern: v_start="<<v_start<<" v_end="<<v_end<<"  ";
            cout<<endl;
        }

        int valid = 0;
        valid = matchHead(p,s,s_start,s_end,ve,v_start,v_end);
        if(valid == 1)
            return true;
        if( valid== -1)
        {
            //cout<<"line 178\n";
            return false;
        }
        valid = matchTail(p,s,s_start,s_end,ve,v_start,v_end);
        if(valid == 1)
            return true;
        if( valid== -1)
        {
           //cout<<"line 186\n";
            return false;
        }

        if(dout2)
        {
            cout<<"After trailing the string: ";
            cout<<" s_start="<<s_start<<" s_end="<<s_end <<"  valid="<<valid<<endl;;
            cout<<"line 140: v_start="<<v_start<<" v_end="<<v_end<<endl;
            cout<<endl;
            cout<<"the new string: ";
            printstring(s,s_start,s_end);
            cout<<endl;
            cout<<"the new vector pattern: ";
            printvector(ve,v_start,v_end);
        }

        return isMatchIndex(s,s_start, s_end,ve,v_start,v_end);
    }
};
