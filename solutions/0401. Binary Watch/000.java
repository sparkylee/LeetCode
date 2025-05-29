class Solution {
    class HM
        {
            int hours;
            int minutes;
            HM(int hours,int minutes)
            {
                this.hours = hours;
                this.minutes= minutes;
            }

        }
        public List<String> readBinaryWatch(int num) {
            List<HM> hmList = new ArrayList<>();
            readBinaryWatchRecursive(num,0,0,0,hmList);
            List<String> readingList = new ArrayList<>();
            for(int i =0; i < hmList.size();i++)
            {
                HM e = hmList.get(i);
                String reading = Integer.toString(e.hours)+":"+String.format("%02d", e.minutes);
                readingList.add(reading);
            }
            return readingList;
        }
        private void readBinaryWatchRecursive(int num,int i,int hours,int minutes, List<HM> readingList)
        {
            if(i>9)
            {
                if(num==0) readingList.add(new HM(hours,minutes));
                return;
            }
            readBinaryWatchRecursive(num,i+1,hours,minutes,readingList);//current bit not chosen
            if(i<=3)  hours += 1<<i;
            else  minutes += 1<<(i-4);//minutes start from the 4th bit
            if(hours > 11 || minutes > 59) return;
            readBinaryWatchRecursive(num-1,i+1,hours,minutes,readingList);//current bit not chosen
        }
}