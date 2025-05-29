class Solution {
public:
    bool wordPattern(string pattern, string str) {
     	vector<std::string> str_vec;
		std::istringstream iss(str);
		for (std::string s; iss >> s;)
			str_vec.push_back(s);
		if (str_vec.size() != pattern.length())
			return false;
		int size = str_vec.size();
		
		for (int i = 0; i < size; i++)
		{
			for (int j = i + 1; j < size;j++)
			{
				if ((pattern[i] == pattern[j]) != (str_vec[i].compare(str_vec[j]) == 0))
					return false;
			}
		}
		return true;
    }
};
