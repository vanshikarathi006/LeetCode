class Solution {
    int[] newParent;
    int[] ans;
    ArrayList<Integer>[] children;
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        // Build the original tree
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        newParent = parent.clone();

        // Stores the latest ancestor for each character
        int[] last = new int[26];
        Arrays.fill(last, -1);

        dfs(0, s, last);

        // Build the modified tree
        for (int i = 0; i < n; i++) {
            children[i].clear();
        }

        for (int i = 1; i < n; i++) {
            children[newParent[i]].add(i);
        }

        ans = new int[n];

        // Calculate subtree sizes
        calculateSize(0);

        return ans;
    }

    void dfs(int node, String s, int[] last) {

        int ch = s.charAt(node) - 'a';

        int previous = last[ch];

        // Change parent if same character ancestor exists
        if (previous != -1) {
            newParent[node] = previous;
        }

        last[ch] = node;

        for (int child : children[node]) {
            dfs(child, s, last);
        }

        // Restore previous ancestor while backtracking
        last[ch] = previous;
    }

    int calculateSize(int node) {
        int size = 1;

        for (int child : children[node]) {
            size += calculateSize(child);
        }

        ans[node] = size;
        return size;
    }
}