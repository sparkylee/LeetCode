class Solution {
public:
    vector<string> fizzBuzz(int n) {
        vector<string> ve;
        for(int i =1; i <= n; i ++)
        {
            if(i%15==0)
            {
                ve.push_back("FizzBuzz");
                continue;
            }
            if(i%3==0)
            {
                ve.push_back("Fizz");
                continue;
            }
            if(i%5==0)
            {
                ve.push_back("Buzz");
                continue;
            }
            char str[100];
            sprintf(str,"%d",i);
            ve.push_back(str);
        }
        return ve;
    }
};