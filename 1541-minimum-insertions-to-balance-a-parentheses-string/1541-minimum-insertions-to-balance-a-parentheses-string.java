class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int insertions = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                open++;
                i++;
            } else {
                int closeCount = 1;
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    closeCount = 2;
                    i += 2;
                } else {
                    i++;
                    insertions++;
                }

                if (open > 0) {
                    open--;
                } else {
                    insertions++; 
                }
            }
        }
        return insertions + 2 * open;
    }
}
