class Solution {
public:
    vector<string> split(const string &s, char delim) {
		stringstream ss(s);
		string item;
		vector<string> tokens;
		while (getline(ss, item, delim)) {
			tokens.push_back(item);
		}
		return tokens;
	}
	vector<int> str2int(vector<string> strv)
	{
		vector<int> ve;
		for (int i = 0; i < strv.size(); i++)
		{
			ve.push_back(atoi(strv[i].c_str()));
		}
		return ve;
	}
		bool checkRemained(vector<int> ve, int k)
	{	
		for (int i = k; i < ve.size(); i++)
		{
			if (ve[i] != 0)
				return true;
		}
		return false;
	}
    int compareVersion(string version1, string version2) {
       		vector<string> strv1 = this->split(version1,'.');
		vector<string> strv2 = this->split(version2, '.');
		vector<int> ve1 = this->str2int(strv1);
		vector<int> ve2 = this->str2int(strv2);
		int size = (ve1.size() < ve2.size()) ? ve1.size() : ve2.size();
		for (int i = 0; i < size; i++)
		{
			if (ve1[i]>ve2[i])
				return 1;
			else if (ve1[i] < ve2[i])
				return -1;
		}
		if (ve1.size()>ve2.size())
		{
			if (checkRemained(ve1, size))
				return 1;
		}
		else if (ve1.size() < ve2.size())
		{
			if (checkRemained(ve2, size))
				return -1;
		}
		else
			return 0;	
		return 0;
    }
    
};