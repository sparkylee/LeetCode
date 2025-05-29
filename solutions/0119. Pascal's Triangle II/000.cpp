class Solution {
public:
    vector<int> getRow(int rowIndex) {
        if(rowIndex<=0)
        { return vector<int>(1,1);}
        if(rowIndex == 1)
        {
            return vector<int>(2,1);
        }
        vector<int> preRow = getRow(rowIndex -1 );
        preRow.push_back(1);
        for(int i = preRow.size() - 2 ;  i >= 1 ;i -- )
        {
            preRow[i] = preRow[i] + preRow[i-1];
        }
        return preRow;
    }
};