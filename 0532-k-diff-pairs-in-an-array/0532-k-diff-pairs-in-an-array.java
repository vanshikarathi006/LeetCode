class Solution {
    public int findPairs(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int left = 0;
        int right = 1;
        int count = 0;
        while(right<n){
            if(left==right){
                right++;
                continue;
            }
            int d = Math.abs(nums[right]-nums[left]);
            if(d > k){
                left++;
            }
            else if(d < k){
                right++;
            }else{
                count ++;
                int a = nums[left];
                int b = nums[right];
                while(right< n && nums[right]==b) right++;
                while(left < n && nums[left]== a) left++;
            }

        }
        return count;
    }
}