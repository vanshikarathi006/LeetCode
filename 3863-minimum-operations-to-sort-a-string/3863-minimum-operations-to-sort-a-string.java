class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean sorted = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }

        if (sorted) return 0;
        char mn = 'z';
        char mx = 'a';

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            mn = (char) Math.min(mn, ch);
            mx = (char) Math.max(mx, ch);
        }
        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) {
            return 1;
        }
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == mn) {
                return 2;
            }
        }
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == mx) {
                return 2;
            }
        }
        if (n == 2) return -1;
        return 3;
    }
}