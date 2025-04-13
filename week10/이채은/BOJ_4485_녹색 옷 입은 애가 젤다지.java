import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] map;

    public static int[] dy = {1, -1, 0, 0};
    public static int[] dx = {0 ,0, 1, -1};

    public static class Node implements Comparable<Node>{
        int y,x,loss;

        public Node(int y, int x, int loss){
            this.y = y;
            this.x = x;
            this.loss = loss;
        }

        @Override
        public int compareTo(Node o){
            return this.loss - o.loss;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testcase = 1;
        N = Integer.parseInt(br.readLine());
        while(N != 0){
            map = new int[N][N];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int totalLoss = bfs();
            sb.append("Problem").append(" ").append(testcase).append(": ").append(totalLoss).append("\n");

            // 다음 테스트 케이스
            testcase++;
            N = Integer.parseInt(br.readLine());
        }

        System.out.println(sb);
    }

    public static int bfs(){
        // dp 초기화
        int[][] dp = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = map[0][0];

        // 우선순위 큐 초기화
        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(0,0,dp[0][0]));

        while(!q.isEmpty()){
            // 현재 갈 수 있는 노드 중 가장 최소비용의 노드 방문
            Node cur = q.poll();

            // 목적지에 도달 시 break
            if(cur.y == N-1 && cur.x == N-1) break;

            // 최소비용 노드에 방문하면, 해당 노드에서 갈 수 있는 노드들의 비용을 업데이트 (최소값으로)
            if(cur.loss > dp[cur.y][cur.x]) continue;
            for(int d = 0; d < 4; d++){
                int nextY = cur.y + dy[d];
                int nextX = cur.x + dx[d];
                if(!isValid(nextY, nextX)) continue;

                if(cur.loss+map[nextY][nextX] < dp[nextY][nextX]){
                    dp[nextY][nextX] = cur.loss+map[nextY][nextX];
                    q.add(new Node(nextY,nextX,dp[nextY][nextX]));
                }
            }
        }
        return dp[N-1][N-1];
    }

    public static boolean isValid(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < N) return true;
        else return false;
    }
}