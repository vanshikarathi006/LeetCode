class Solution {
    List<String> ans = new ArrayList<>();
    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } 
            else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }
        backtrack(s, 0, left, right, 0, new StringBuilder());

        return ans;
    }

    private void backtrack(String s, int index,int leftRemove, int rightRemove, int balance, StringBuilder current) {
        if (balance < 0) {
            return;
        }
        if (index == s.length()) {
            if (balance == 0 && leftRemove == 0 && rightRemove == 0) {
                String str = current.toString();
                 if (!ans.contains(str)) {
                    ans.add(str);
                }
            }
            return;
        }
        char c = s.charAt(index);
        if (c == '(' && leftRemove > 0) {
            backtrack(s, index + 1,leftRemove - 1,rightRemove,balance,current);
        }
        if (c == ')' && rightRemove > 0) {
            backtrack(s, index + 1, leftRemove, rightRemove - 1, balance, current);
        }
        current.append(c);
        if (c == '(') {
            backtrack(s, index + 1, leftRemove, rightRemove, balance + 1, current);
        } 
        else if (c == ')') {
            backtrack(s, index + 1, leftRemove, rightRemove, balance - 1, current);
        } 
        else {
            backtrack(s, index + 1, leftRemove, rightRemove, balance, current);
        }
        current.deleteCharAt(current.length() - 1);
    }
}