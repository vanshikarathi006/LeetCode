class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int low = 0;
        int high = n - 1;
        while(low <= high){
            int left = nums[low] * nums[low];
            int right = nums[high] * nums[high];
            if(left > right){
                ans[n-1] = left;
                low++;
                n--;
            }else{
                ans[n-1] = right;
                n--;
                high--;
            }
        }
        return ans;

        // BRUTE FORCE
        // for(int i = 0; i < nums.length; i++){
        //     nums[i] *= nums[i];
        // }
        // Arrays.sort(nums);
        // return nums;
    }
}