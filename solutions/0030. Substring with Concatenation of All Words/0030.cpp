

class Solution {
    const bool dout = false;
    const bool dout0 = false;
    const bool dout1 = false;
    const bool dout2 = false;//verify a group
    const bool dout3 = false;//verify a group

    int WL ; //the word lenghth
    int N  ; // the new words size
    int M  ; // the old words size

 //http://stackoverflow.com/questions/8317508/hash-function-for-a-string
   unsigned int hash_str(string s)
    {
       unsigned int A = 54059 ;
       unsigned int B = 76963 ;
       unsigned int C = 86969 ;
       unsigned int  h = 37;
       for(int i =0; i < s.length(); i++)
       {
         h = (h * A) ^ (s[i] * B);
       }
       return h; // or return h % C;
    }
    unsigned int group_hash(vector<unsigned> & words_hash , vector<int> & words_used)
    {
        unsigned h = 0x0;
        for(int i = 0 ; i < words_hash.size(); i ++)
        {
            for(int j = 0; j < words_used[i]; j ++)
                h = h^ words_hash[i];
        }
        return h;
    }


    bool verify_group(vector<string> & group_str, vector<unsigned> & group_hash, int k, vector<string>& words, vector<unsigned> & words_hash, vector<int> words_used)
    {
        if(k == M)//M the original words size
        {
            if(dout2)
                cout<<"reached the end of the group\n";
            return true;
        }
        for(int i = 0; i < N; i ++)// N the new words size
        {
            if(dout2)
            {
                cout<<i<<" "<< group_hash[k] << " "<< words_hash[i] <<" "<< group_str[k]<< " " <<words[i] <<endl;
            }
            if(words_used[i] > 0 && group_hash[k]==words_hash[i] && group_str[k]==words[i])
            {
                if(dout2)
                {
                    cout<<"The "<<k<<"th word "<<group_str[k] << " is matched\n";
                }
                words_used[i] --;
                bool group_matched = verify_group(group_str,group_hash,k+1,words,words_hash,words_used);
                if(group_matched)
                    return true;
                words_used[i] ++;
            }
        }
        if(dout2)
        {
            cout<<k<<"th word found no match\n";
        }
        return false;
    }
    void insertNewWord(vector<string>& wordsNew, vector<unsigned> & words_hash, vector<int> & words_used, unsigned h, string w)
    {
        for(int i = 0; i < wordsNew.size(); i ++)
        {
            if(words_hash[i] == h)
            {
                if(wordsNew[i] == w)
                {
                    words_used[i] ++;
                    if(dout3)
                        cout<<"string "<<w<<" found a duplication in the new word vector!"<<endl;
                    return;
                }
                else
                    break;
            }
        }
        wordsNew.push_back(w);
        words_hash.push_back(h);
        words_used.push_back(1);
        if(dout3)
                cout<<"string "<<w<<" is added to the new word vector!"<<endl;
        return;
    }
public:
    vector<int> findSubstring(string s, vector<string>& words) {

        this->WL = words[0].length();
        this->M  = words.size(); //the old words size
        vector<unsigned> words_hash;
        vector<int> words_used;
        set<unsigned> words_hash_set;
        vector<string> wordsNew;
        for(int i = 0; i < words.size(); i ++)
        {
            unsigned h = hash_str(words[i]);
            words_hash_set.insert(h);
            insertNewWord(wordsNew,words_hash,words_used,h,words[i]);
        }
        this->N  = wordsNew.size();// the new words size


        if(dout3)
        {
            for(int i = 0; i < this->N ; i ++)// the new words size
            {
                cout<< "The " <<i <<"th word: " <<wordsNew[i] <<"  hashcode=" <<words_hash[i] <<" duplicateCnt="<< words_used[i]<<endl;
            }
        }
        unsigned words_group_h = group_hash(words_hash, words_used);
        if(dout)
        {
            cout<<"the new words-group hash code is " << words_group_h<<endl;
        }
        vector<unsigned> sword_h;
        for(int i = 0 ; i < s.length() - (WL - 1); i ++)
        {
            unsigned h = 0;
            string sstr = s.substr(i, WL);
            h = hash_str(sstr);
            if(dout1)
                cout<< "The hash code for string " << sstr << " is " << h <<endl;
            sword_h.push_back(h);

        }

        vector<int> loc;
        if(dout)
            cout<<"The entire string: "<<s<<endl;
        for(int i = 0; i < s.length() - (WL*M-1); i ++) // the old words size
        {
            string sstr = s.substr(i, WL*M);  // the old words size
            unsigned this_group_h = 0x0;
            bool selected = true;

            vector<unsigned> group_hash;
            vector<string> group_str;
            for(int j = i ; j < i + WL * M; j = j + WL )  // the old words size
            {
                if(words_hash_set.count(sword_h[j]) != 1)
                    {
                        selected = false;
                        break;
                    }

                group_hash.push_back(sword_h[j]);
                group_str.push_back(s.substr(j, WL));

                this_group_h = this_group_h ^ sword_h[j];
                if(dout0)
                {
                    cout<<"j = " << j <<endl;
                }
            }
            if(dout1)
                cout<<"The string to be verified: "<<sstr<<" hashcode="<<this_group_h<<endl;
            if(this_group_h != words_group_h)
                selected = false;

            if(dout2 && selected)
            {
                cout<< "The " << i <<"th string to be verified is "<< sstr<< endl;
            }
            if(selected)
            {
                bool group_matched = false;

                group_matched = verify_group(group_str,group_hash,0,wordsNew,words_hash,words_used);
                if(group_matched)
                    loc.push_back(i);
            }
        }


        return loc;


    }
};

