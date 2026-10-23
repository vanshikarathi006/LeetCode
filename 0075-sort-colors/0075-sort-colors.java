class Solution {
    public void sortColors(int[] nums) {
        int red = 0;
        int white = 0;
        int blue = 0;
        for(int val : nums){
            if(val ==0){
                red++;
            }else if(val ==1){
                white++;
            }else{
                blue++;
            }
        }
        int p = 0;
        while(red != 0){
            nums[p] = 0;
            red--;
            p++;
        }
        while(white != 0){
            nums[p] = 1;
            white--;
            p++;
        }
        while(blue != 0){
            nums[p] = 2;
            blue--;
            p++;
        }
    }
}