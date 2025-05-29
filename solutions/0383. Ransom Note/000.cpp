class Solution {
public:
    bool canConstruct(string ransomNote, string magazine) {
        int lc[26];
        memset(lc,0,sizeof(lc));
        for(string::iterator it = magazine.begin(); it != magazine.end(); ++it) {
            lc[*it-97] ++;
        }
        for(string::iterator it = ransomNote.begin(); it != ransomNote.end(); ++it) {
            if(lc[*it-97] > 0)
                lc[*it-97] -- ;
            else 
                return false;
        }
        return true;
    }
};