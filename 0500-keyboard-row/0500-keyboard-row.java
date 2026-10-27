class Solution {
    public String[] findWords(String[] words) {
        List<String> ans = new ArrayList<>();
        String row1 = "qwertyuiop";
        String row2 = "asdfghjkl";
        String row3 = "zxcvbnm";
        for(String word : words){
            int[] temp = new int[3];
            for(char ch : word.toLowerCase().toCharArray()){
                if(row1.indexOf(ch) != -1){
                    temp[0] = 1;
                }else if(row2.indexOf(ch) != -1){
                    temp[1] = 1;
                }else{
                    temp[2] = 1;
                }
            }
            int sum = temp[0] + temp[1] + temp[2];
            if(sum == 1){
                ans.add(word);
            }
        }
        String[] result = new String[ans.size()];
        for(int i = 0; i < ans.size(); i++){
            result[i] = ans.get(i);
        }
        return result;
        // List<String> ans = new ArrayList<>();
        // for(String word : words){
        //     int[] rowIndicator = new int[3];
        //     if(word.contains("qwertyuiop" || "QWERTYUIOP")){
        //         rowIndicator[0] = 1;
        //     }else if (word.contains("asdfghjkl" || "ASDFGHJKL")){
        //         rowIndicator[1] = 1;
        //     }else if()
        // }
    }
}