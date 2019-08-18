public class lc744 {
    private int searchLoc(char[] letters, int start, int end, char target) {
        if (start + 1 >= end) {
            return start;
        }
        int middle = (start + end) / 2;
        if (letters[middle] <= target)
            return searchLoc(letters, middle, end, target);
        return searchLoc(letters, start, middle, target);
    }

    public char nextGreatestLetter(char[] letters, char target) {
        if (target >= letters[letters.length - 1] || target < letters[0])
            return letters[0];
        int loc = searchLoc(letters, 0, letters.length - 1, target);
        return letters[loc + 1];
    }
}
