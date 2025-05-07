class Solution(object):
    def recurCombine(self,mylist,n,result,i,k,item):
        if k ==0:
            result.append(list(item));
            return;
        if i > n - k :
            return;
        item.append(mylist[i]);
        self.recurCombine(mylist,n,result,i+1,k-1,item);
        del item[-1];
        self.recurCombine(mylist, n, result, i + 1, k, item);
        return;
    def combine(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: List[List[int]]
        """
        mylist = list(range(1,n+1));
        result = [];
        item = [];
        self.recurCombine(mylist,n,result,0,k,item);
        return result;
