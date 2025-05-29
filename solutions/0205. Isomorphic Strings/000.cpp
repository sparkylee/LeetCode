class Solution {
public:
    bool isIsomorphic(string s, string t) {
        int mapST[128],mapTS[128];
        memset(mapST,-1,sizeof(mapST));
        memset(mapTS,-1,sizeof(mapTS));
        for(int i = 0; i< s.length(); i++)
        {
            int si = s[i];
            int ti = t[i];
            if(mapST[si] != -1 and mapST[si] != ti)
            return false;
            if(mapTS[ti] != -1 and mapTS[ti] != si)
            return false;
            mapTS[ti] = si;
            mapST[si] = ti;
            
        }
        return true;
    }
};