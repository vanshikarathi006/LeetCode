class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length;
        int ans = 0;
        int low = 0;
        int high = n - 1;
        while(low <= high){
            if(people[low] + people[high] <= limit ){
                low++;
                high--;
            }else{
                high--;
            }
            ans++;
        }
        return ans;
    }
}