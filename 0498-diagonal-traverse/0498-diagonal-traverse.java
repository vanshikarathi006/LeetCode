class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        if(mat == null || mat.length == 0) return new int[0];
        int n = mat.length;
        int m = mat[0].length;
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int key = i + j;
                map.putIfAbsent(key, new ArrayList<>());
                map.get(key).add(mat[i][j]);
            }
        }
        List<Integer> ans = new ArrayList<>();
        boolean flip = true;
        for(int i = 0; i < n + m; i++){
            List<Integer> diagonal = map.get(i);
            if(diagonal == null) continue;
            if(flip){
                Collections.reverse(diagonal);
            }
            ans.addAll(diagonal);
            flip = !flip;
        }
        int[] res = new int[ans.size()];
        for(int i = 0; i < res.length; i++){
            res[i] = ans.get(i);
        }
        return res;
    }
}