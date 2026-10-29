class Solution {
    List<Integer>[] adj;
    int[] parent, depth, size, heavy, head, pos, nodeAt;
    int[] segTree;
    char[] chars;
    int timer;
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        chars = s.toCharArray();
        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        nodeAt = new int[n];
        Arrays.fill(heavy, -1);
        dfs(0, -1);
        timer = 0;
        decompose(0, 0);
        segTree = new int[4 * n];
        build(1, 0, n - 1);

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                chars[u] = c;
                update(1, 0, n - 1, pos[u], 1 << (c - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int mask = queryPath(u, v);

                ans.add((mask & (mask - 1)) == 0);
            }
        }
        return ans;
    }

    void dfs(int u, int p) {
        parent[u] = p;
        size[u] = 1;
        int maxSize = 0;
        for (int v : adj[u]) {
            if (v == p) continue;
            depth[v] = depth[u] + 1;
            dfs(v, u);
            size[u] += size[v];
            if (size[v] > maxSize) {
                maxSize = size[v];
                heavy[u] = v;
            }
        }
    }

    void decompose(int u, int h) {
        head[u] = h;
        pos[u] = timer;
        nodeAt[timer] = u;
        timer++;
        if (heavy[u] != -1) {
            decompose(heavy[u], h);
        }
        for (int v : adj[u]) {
            if (v == parent[u] || v == heavy[u]) continue;
            decompose(v, v);
        }
    }

    void build(int node, int l, int r) {
        if (l == r) {
            int u = nodeAt[l];
            segTree[node] = 1 << (chars[u] - 'a');
            return;
        }
        int mid = (l + r) / 2;
        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);
        segTree[node] = segTree[node * 2] ^ segTree[node * 2 + 1];
    }

    void update(int node, int l, int r, int idx, int value) {
        if (l == r) {
            segTree[node] = value;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) {
            update(node * 2, l, mid, idx, value);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, value);
        }
        segTree[node] = segTree[node * 2] ^ segTree[node * 2 + 1];
    }

    int rangeQuery(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) {
            return segTree[node];
        }
        int mid = (l + r) / 2;
        return rangeQuery(node * 2, l, mid, ql, qr)
                ^ rangeQuery(node * 2 + 1, mid + 1, r, ql, qr);
    }

    int queryPath(int u, int v) {
        int mask = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }
            mask ^= rangeQuery(1, 0, chars.length - 1, pos[head[u]], pos[u]);
            u = parent[head[u]];
        }
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        mask ^= rangeQuery(1, 0, chars.length - 1, pos[u], pos[v]);
        return mask;
    }
}