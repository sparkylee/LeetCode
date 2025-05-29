class Solution {
    class Item {
        int freq;
        int left;
        int right;

        Item(int freq, int left, int right) {
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
    }

    public int findShortestSubArray(int[] nums) {
        Map<Integer, Item> frequencies = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            final int ii = i;
            frequencies.computeIfPresent(n, (k, v) -> {
                        v.freq += 1;
                        v.left = Math.min(ii, v.left);
                        v.right = Math.max(ii, v.right);
                        return v;
                    }
            );
            frequencies.putIfAbsent(n, new Item(1, ii, ii));
        }
        int fm = 0;
        Iterator it = frequencies.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Item> pair = (Map.Entry<Integer, Item>) it.next();
            fm = Math.max(fm, pair.getValue().freq);
        }
        int len = -1;
        it = frequencies.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Item> pair = (Map.Entry<Integer, Item>) it.next();
            Item item = pair.getValue();
            if (item.freq == fm) {
                int lenNew = item.right - item.left + 1;
                if (len == -1 || lenNew < len)
                    len = lenNew;
            }
        }
        return len;
    }
}