class Solution {
public:
    string getHint(string secret, string guess) {
        int A = 0, B = 0;
        int D1[10],D2[10];
        memset(D1, 0, sizeof(D1));
        memset(D2, 0, sizeof(D2));
        for (int i=0;i<secret.length(); i++)
        {
            if(secret[i]==guess[i])
                A++;
            else
            {
                D1[secret[i]-0x30] ++;
                D2[guess[i]-0x30] ++;
            }
        }
        for (int j=0;j<10;j++)
        {
            if(D1[j] != 0 and D2[j] != 0)
                B += (D1[j]<D2[j])?D1[j]:D2[j];
        }
        return std::to_string(A) + "A"+std::to_string(B) + "B";
        
    }
};