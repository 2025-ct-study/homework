import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;

    public static class God {
        int x, y;
        public God(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static class Edge implements Comparable<Edge>{
        int g1, g2;
        double dist;

        public Edge(int g1, int g2, double dist){
            this.g1 = g1;
            this.g2 = g2;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o){
            return Double.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        God[] gods = new God[N+1];
        parent = new int[N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            gods[i] = new God(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            parent[i] = i;
        }

        // 이미 연결되어 있는 부분 union find
        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int g1 = Integer.parseInt(st.nextToken());
            int g2 = Integer.parseInt(st.nextToken());
            union(g1,g2);
        }

        // 우주신들간의 모든 간선 구하기
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i = 1; i <= N; i++){
            for(int j = i+1; j <= N; j++){
                pq.offer(new Edge(i,j,distance(gods[i], gods[j])));
            }
        }

        // MST - 크루스칼 알고리즘
        // dist 짧은 순으로 union find
        double minDist = 0;
        while(!pq.isEmpty()){
            Edge curEdge = pq.poll();

            if(find(curEdge.g1) != find(curEdge.g2)){
                union(curEdge.g1, curEdge.g2);
                minDist += curEdge.dist;
            }
        }

        System.out.printf("%.2f", minDist);
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a < b) parent[b] = a;
        else if(a > b) parent[a] = b;
    }

    public static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static double distance(God g1, God g2){
        return Math.sqrt(Math.pow(g1.x-g2.x, 2) + Math.pow(g1.y-g2.y, 2));
    }
}