public class IndexMaxPQ<Key extends Comparable<Key>> {
    private int N;  //number of elements on PQ
    private int[] pq;   //binary heap using 1-based indexing
    private int[] qp;   //index of key value in heap
    private Key[] keys; //key[i] priority of i

    public IndexMaxPQ(int maxN) {
        N = 0;
        keys = (Key[]) new Comparable[maxN+1];
        pq = new int[maxN+1];
        qp = new int[maxN+1];
        for (int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }
    }
    public int size() { return N; }
    public boolean isEmpty() { return N == 0; }
    public boolean contains(int i) { return qp[i] != -1; }

    //insert element
    public void insert(int i,Key key) {
        N++;
        qp[i] = N;
        keys[i] = key;
        pq[N] = i;
        swim(N);
    }

    public int delMax(){
        int max = pq[1];
        exch(1,N--);
        sink(1);

        qp[max] = -1;       //delete index of index max in qp(in heap)
        keys[max] = null;
        pq[N+1] = -1;

        return max;
    }

    public void change(int i, Key key) {
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    public void increaseKey(int i, Key key) {
        keys[i] = key;
        swim(qp[i]);
    }

    public void decreaseKey(int i, Key key) {
        keys[i] = key;
        sink(qp[i]);
    }

    public void delete(int i) {
        int index = qp[i];
        exch(index, N--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    public boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
    private void exch(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public int maxIndex() {
        return pq[1];
    }

    public Key maxKey() {
        return  keys[pq[1]];
    }




}
