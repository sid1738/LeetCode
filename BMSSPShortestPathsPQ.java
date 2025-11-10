import java.util.*;

public class BMSSPShortestPathsPQ {
    static class Edge {
        int to;
        double weight;
        Edge(int to, double weight) { this.to = to; this.weight = weight; }
    }
    static class State {
        List<List<Edge>> adj;
        double[] d;
        int[] pred;
        int n;
        State(int n) {
            this.n = n;
            adj = new ArrayList<>();
            for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
            d = new double[n];
            Arrays.fill(d, Double.POSITIVE_INFINITY);
            pred = new int[n];
            Arrays.fill(pred, -1);
        }
        void addEdge(int u, int v, double w) { adj.get(u).add(new Edge(v, w)); }
    }
    static class Pair implements Comparable<Pair> {
        int v;
        double dist;
        Pair(int v, double dist) { this.v = v; this.dist = dist; }
        public int compareTo(Pair o) { return Double.compare(this.dist, o.dist); }
    }
    
    // Tuning parameters as in paper
    static int k(int n) { return Math.max(1, (int) Math.floor(Math.pow(Math.log(n), 1.0/3))); }
    static int t(int n) { return Math.max(1, (int) Math.floor(Math.pow(Math.log(n), 2.0/3))); }
    
    // BMSSP main function with PriorityQueue for pivots/frontier
    static void BMSSP(State state, int l, double B, PriorityQueue<Pair> S, Set<Integer> U, int level) {
        int n = state.n;
        int k = k(n);
        int t = t(n);
        printIndented(level, "BMSSP(l=" + l + ") : pivots/heap size=" + S.size() + ", B=" + B);

        if (l == 0) {
            printIndented(level, "BaseCase: Mini-Dijkstra from S.");
            U.addAll(BaseCase(state, B, S));
            printIndented(level, "Base case completed: " + U);
            return;
        }

        // Find pivots
        PriorityQueue<Pair> pivots = new PriorityQueue<>(S);
        Set<Integer> W = new HashSet<>();
        findPivots(state, B, pivots, W, level);

        // Partial priority queue as per the main routine
        PriorityQueue<Pair> D = new PriorityQueue<>(pivots);

        Set<Integer> accumU = new HashSet<>();
        double B0 = B;
        for (Pair p : pivots) B0 = Math.min(B0, p.dist);

        while (accumU.size() < k*k*Math.pow(2, l*t) && !D.isEmpty()) {
            List<Pair> toExpand = new ArrayList<>();
            for (int cnt = 0; cnt < t && !D.isEmpty(); cnt++) { // batch extract
                toExpand.add(D.poll());
            }
            PriorityQueue<Pair> Si = new PriorityQueue<>(toExpand);

            Set<Integer> Ui = new HashSet<>();
            double Bi = toExpand.stream().mapToDouble(x -> x.dist).min().orElse(B);
            BMSSP(state, l-1, Bi, Si, Ui, level+1);
            accumU.addAll(Ui);

            for (int u : Ui) {
                for (Edge e : state.adj.get(u)) {
                    if (state.d[u] + e.weight < state.d[e.to]) {
                        printIndented(level, "Update: d[" + e.to + "] = " + state.d[e.to] + " --> " + (state.d[u]+e.weight) + " via " + u);
                        state.d[e.to] = state.d[u] + e.weight;
                        state.pred[e.to] = u;
                        D.add(new Pair(e.to, state.d[e.to]));
                    }
                }
            }
        }
        U.addAll(accumU);
        printIndented(level, "BMSSP level " + l + " finished, completed set U: " + U);
    }

    // FindPivots using PQ for source set S
    static void findPivots(State state, double B, PriorityQueue<Pair> pivots, Set<Integer> W, int level) {
        int n = state.n;
        int k = k(n);
        PriorityQueue<Pair> curr = new PriorityQueue<>(pivots);

        for (int i = 0; i < k; i++) {
            PriorityQueue<Pair> next = new PriorityQueue<>();
            while (!curr.isEmpty()) {
                Pair u = curr.poll();
                for (Edge e : state.adj.get(u.v)) {
                    if (state.d[u.v] + e.weight < state.d[e.to] && state.d[u.v] + e.weight < B) {
                        state.d[e.to] = state.d[u.v] + e.weight;
                        state.pred[e.to] = u.v;
                        next.add(new Pair(e.to, state.d[e.to]));
                        W.add(e.to);
                        printIndented(level, String.format("Relax: %d → %d : %.2f", u.v, e.to, state.d[e.to]));
                    }
                }
            }
            curr = next;
        }
        printIndented(level, "FindPivots: pivots=" + pivots + ", W=" + W);
    }

    // Modified BaseCase: Dijkstra from PQ S
    static Set<Integer> BaseCase(State state, double B, PriorityQueue<Pair> S) {
        Set<Integer> result = new HashSet<>();
        PriorityQueue<Pair> heap = new PriorityQueue<>(S);
        while (!heap.isEmpty() && result.size() < k(state.n) + 1) {
            Pair p = heap.poll();
            result.add(p.v);
            for (Edge e : state.adj.get(p.v)) {
                if (state.d[p.v] + e.weight < state.d[e.to] && state.d[p.v] + e.weight < B) {
                    state.d[e.to] = state.d[p.v] + e.weight;
                    state.pred[e.to] = p.v;
                    heap.add(new Pair(e.to, state.d[e.to]));
                }
            }
        }
        return result;
    }

    // Indented output
    static void printIndented(int level, String msg) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println(msg);
    }

    // Demo main (same as before)
    public static void main(String[] args) {
        int n = 5;
        State state = new State(n);
        state.addEdge(0, 1, 2);
        state.addEdge(0, 2, 5);
        state.addEdge(1, 2, 1);
        state.addEdge(1, 3, 2);
        state.addEdge(2, 3, 1);
        state.addEdge(3, 4, 3);
        state.addEdge(2, 4, 10);
        state.d[0] = 0;

        PriorityQueue<Pair> S = new PriorityQueue<>();
        S.add(new Pair(0, 0));
        Set<Integer> U = new HashSet<>();
        BMSSP(state, (int)(Math.log(n)/t(n)), Double.POSITIVE_INFINITY, S, U, 0);
        System.out.println("Final shortest distances:");
        for (int i = 0; i < n; i++) {
            System.out.printf("d[%d] = %.2f, pred=%d\n", i, state.d[i], state.pred[i]);
        }
    }
}
