class Solution(object):
    pv=[1,1,2,6,24,120,720,5040,40320,362880];
    def getPerSeq(self, seq, avail,n,k):
        if  k > self.pv[n]:
            return;
        if len(avail) == 0:
            return;
        if k==0 or n== 0:
            seq.extend(avail);
            return ;
        i = 0;
        cur_v = 0;
        if k < self.pv[n - 1]:
            seq.append(avail[i]);
            del avail[i];
            self.getPerSeq(seq, avail, n - 1, k);
            return;
        next_k = 0;
        for i in range(1,len(avail)):
            cur_v = i* self.pv[n - 1];
            if cur_v == k:
                next_k = 0;
                seq.append(avail[i]);
                del avail[i];
                self.getPerSeq(seq, avail, n - 1, next_k);
                return;
            if cur_v > k:
                i = i - 1;
                break;
        cur_v = i * self.pv[n - 1];
        next_k = k - cur_v ;
        seq.append(avail[i]);
        del avail[i];
        self.getPerSeq(seq,avail,n-1,next_k);
        return;
    def getPermutation(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: str
        """
        k = k -1;
        seq = [];
        avail = [x+1 for x in range(n)];
        self.getPerSeq(seq,avail,n,k);
        return ''.join(str(x) for x in seq);

