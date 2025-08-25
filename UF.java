package UnionFind;

public class UF {
    private int[] id;
    private int[] size;
    private int count;

    public UF(int n) {
        id = new int[n];
        size = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public int count() { return count; }

    public boolean connected(int p, int q) { return find(p) == find(q); }

    public int find(int p) {
//       if (id[p] != p) {
//            id[p] = find(id[p]); //recursive
//        }
//        return id[p];
        int root = p;
        while (id[root] != p) { root = id[root]; }
        while (p != root) {
            int next = id[p];
            id[p] = root;
            root = next;
        }
        return root;
    }

    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);
        if (pid == qid) { return; }
        else if (size[pid] < size[qid]) {
            id[pid] = qid;
            size[qid] += size[pid];
        }
        else {
            id[qid] = pid;
            size[pid] += size[qid];
        }
        count--;
    }


}
