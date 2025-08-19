package BiWeeklyContest163;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Arrays;
import java.util.LinkedList;

class Solution {
    public int minCost(int n, int[][] edges) {
        List<List<List<Integer>>>adj=new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int edge[]: edges){
            adj.get(edge[0]).add(Arrays.asList(edge[1], edge[2]));
            adj.get(edge[1]).add(Arrays.asList(edge[0], 2*edge[2]));
        }

        PriorityQueue<List<Integer>> q = new PriorityQueue <>( (a, b) -> a.get(1)-b.get(1));
        boolean[] visited = new boolean[n]; 
        
        q.add(Arrays.asList(0, 0)); // Starting from node 0 with cost 0
        while(!q.isEmpty()){
            List<Integer> current = q.poll();
            int node = current.get(0);
            int cost = current.get(1);
            
            if (node == n - 1) {
                return cost; // Reached the last node
            }
            
            if (visited[node]) continue; // Skip if already visited
            visited[node] = true;
            
            for (List<Integer> neighbor : adj.get(node)) {
                int nextNode = neighbor.get(0);
                int edgeCost = neighbor.get(1);
                
                if (!visited[nextNode]) {
                    q.add(Arrays.asList(nextNode, cost + edgeCost));
                }
            }
        }
        return -1;
    }
}
