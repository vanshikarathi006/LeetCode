class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n=position.length;
        int low= 1;
        int high = position[n-1]-low;
        int ans=0;
        while(low<=high){
            int mid= low + (high-low)/2;
             if(isPossible(position, m, mid)){
                ans = mid;
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }return ans;
    }
    public boolean isPossible(int[] arr, int m, long mid) {
        int count = 1;
        int last = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] - last >= mid) {
                count++;
                last = arr[i];
            }
            if(count >= m) {
                return true;
            }
        }
        return false;
    }
}