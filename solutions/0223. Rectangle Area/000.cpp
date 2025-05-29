class Solution {
public:
    int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
  int i,j, m,n;                                                                   
    i = (A>E)?A:E;                                                                  
    j = (B>F)?B:F;                                                                  
    m = (C<G)?C:G;                                                                  
    n = (D<H)?D:H;                                                                  
    int shared_area = 0;                                                            
    if(m>i and n>j)                                                                 
    {                                                                               
      shared_area = (m-i)*(n-j);                                                    
    }                                                                               
    return (C-A)*(D-B) + (G-E)*(H-F) - shared_area;

    }
};