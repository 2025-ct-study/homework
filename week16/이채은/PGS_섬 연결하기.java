import java.util.*;

class Solution {

    public int[] parent;

    public int solution(int n, int[][] costs) {

        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }

        Arrays.sort(costs, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return Integer.compare(o1[2], o2[2]);
            }
        });

        int answer = 0;
        for(int i = 0; i < costs.length; i++){
            int a = costs[i][0];
            int b = costs[i][1];

            if(find(a) != find(b)){
                union(a, b);
                answer += costs[i][2];
            }
        }

        return answer;
    }

    public int find(int a){
        if(parent[a] == a) return a;

        return parent[a] = find(parent[a]);
    }

    public void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA < rootB) parent[rootB] = rootA;
        else parent[rootA] = rootB;
    }
}