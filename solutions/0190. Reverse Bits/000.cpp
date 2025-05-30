class Solution {
public:
    uint32_t reverseBits(uint32_t n) {
        	uint32_t a = 0;		
		for (int i = 0; i < 32; i++)
		{
			int b = (n >> i) & 0x1;
			int m = b << (31 - i);
			a = a | m;
		}
		return a;
    }
};