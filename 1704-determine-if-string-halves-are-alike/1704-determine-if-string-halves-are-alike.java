class Solution {
    public boolean halvesAreAlike(String s) {
        int length = s.length();
        int mid = length / 2;
        String vowels = "aeiouAEIOU";
        int i = 0;
        int j = mid;
        int left = 0;
        int right = 0;
        while(left < mid && j < length){
            if(vowels.indexOf(s.charAt(i))!= -1){
                left++;
            }
            if(vowels.indexOf(s.charAt(j))!= -1){
                right++;
            }
            i++;
            j++;
        }
        return left == right;
    }
}