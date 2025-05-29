class Solution {
    String date;

    private int getVal(int start, int end) {
        return Integer.valueOf(date.substring(start, end));
    }

    private boolean isLeap(int year) {
        return !((year % 100 == 0 && year % 400 != 0) || year % 4 != 0);
    }

    private int getMonthLen(boolean leap, int month) {
        if ((month <= 7 && month % 2 == 1) || (month >= 8 && month % 2 == 0)) return 31;
        if (month == 2) return leap ? 29 : 28;
        return 30;
    }

    public int dayOfYear(String date) {
        this.date = date;
        int year = getVal(0, 4);
        int month = getVal(5, 7);
        int dat = getVal(8, 10);
        int count = 0;
        boolean leap = isLeap(year);
        for (int i = 1; i < month; i++)
            count += getMonthLen(leap, i);
        return count + dat;
    }
}