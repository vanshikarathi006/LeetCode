class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int low = 0;
        int high = (m*n)-1;
        while(low <= high){
            int mid = low + (high - low)/2;
            int row = mid / m;
            int col = mid % m;
            if(matrix[row][col] == target){
                return true;
            }else if(matrix[row][col] < target){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return false;
        // for(int i = 0; i < m; i++){
        //     for(int j = 0; j < n; j++){
        //         if(matrix[i][j] == target){
        //             return true;
        //         }
        //     }
        // }
        // return false;
    }
}
// class Solution {
//     public boolean searchMatrix(int[][] matrix, int target) {
//         int m = matrix.length;
//         int n = matrix[0].length;

//         for (int i = 0; i < m; i++) {

            
//             if (matrix[i][0] <= target &&
//                 matrix[i][n - 1] >= target) {

//                 return binarySearch(matrix, i, n - 1, target);
//             }
//         }

//         return false;
//     }

//     private boolean binarySearch(int[][] matrix, int i, int j, int target) {
//         int low = 0;
//         int high = j;

//         while (low <= high) {
//             int mid = low + (high - low) / 2;

//             if (matrix[i][mid] == target) {
//                 return true;
//             } else if (matrix[i][mid] < target) {
//                 low = mid + 1;
//             } else {
//                 high = mid - 1;
//             }
//         }

//         return false;
//     }
// }