import java.util.Comparator;
import java.util.NoSuchElementException;

public class MaxPQ <Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;
    private Comparator<Key> cmp;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key key) {
        if (N == pq.length) {
            System.out.println("MaxPQ overflow");
            return;
        }
        pq[++N] = key;
        swim(N);
    }

    public Key delMax() {
        if (isEmpty()) { throw new NoSuchElementException("MaxPq is underflow");
        }
        Key max = pq[1];
        exch(1,N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    public boolean less(int i, int j) {
        if(cmp == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j])  <0;
        }
        else {
            return cmp.compare(pq[i], pq[j]) < 0;
        }
    }

    public void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    public void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if(j < N && less(j, j+1)) j++;
            if(!less(k, j)) break;
            exch(k, j);

        }
    }

    public void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public void display() {
        for (int i = 1; i <= N; i++) {
            System.out.print(pq[i] + " ");
        }
    }
}
