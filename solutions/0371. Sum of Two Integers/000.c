int getSum(int a, int b) {
     int aMask = 1;
     int bMask = 1;
     int cMask = 0;
     int C = 0;
     int Q = 0;
     for(int i=0; i < 32; i++) 
     {
            int QMask =  ( (a & aMask) ^ (b & bMask) ^ cMask );
            Q     =  QMask | Q;
            cMask = ((a & aMask) & (b & bMask) ) | ((a & aMask) & cMask ) | ((b & bMask) & cMask );
            aMask = (aMask << 1);
            bMask = (bMask << 1);
            cMask = (cMask << 1);
     }
    return Q;
}