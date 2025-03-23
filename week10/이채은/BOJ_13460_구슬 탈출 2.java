import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int M;
    public static char[][] map;
    public static int[] dy = {1, -1, 0, 0};
    public static int[] dx = {0 ,0, 1, -1};

    public static class RedBlue{
        int ry, rx, by, bx, depth;

        public RedBlue(int ry, int rx, int by, int bx, int depth){
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        String line;
        int ry = 0, rx = 0, by = 0, bx = 0;
        for(int i = 0; i < N; i++){
            line = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'R'){
                    ry = i;
                    rx = j;
                }
                if(map[i][j] == 'B'){
                    by = i;
                    bx = j;
                }
            }
        }

        // bfs
        System.out.println(bfs(ry,rx,by,bx));
    }

    public static int bfs(int ry, int rx, int by, int bx){
        Queue<RedBlue> q = new ArrayDeque<>();
        q.add(new RedBlue(ry,rx,by,bx,0));

        boolean[][][][] visited = new boolean[N][M][N][M];
        visited[ry][rx][by][bx] = true;

        while(!q.isEmpty()){
            RedBlue cur = q.poll();
            // 10번 움직였으면 -1
            if(cur.depth == 10) return -1;

            for(int d = 0; d < 4; d++){
                // 빨강 움직이기
                int nextRY = cur.ry;
                int nextRX = cur.rx;
                while(map[nextRY+dy[d]][nextRX+dx[d]] != '#'){
                    nextRY += dy[d];
                    nextRX += dx[d];
                    if(map[nextRY][nextRX] == 'O') break;
                }

                // 파랑 움직이기
                int nextBY = cur.by;
                int nextBX = cur.bx;
                while(map[nextBY+dy[d]][nextBX+dx[d]] != '#'){
                    nextBY += dy[d];
                    nextBX += dx[d];
                    if(map[nextBY][nextBX] == 'O') break;
                }

                // 파랑이 구멍에 빠졌으면 continue => 다른 방향 탐색
                if(map[nextBY][nextBX] == 'O'){
                    continue;
                }
                // 빨강이 구멍에 빠졌으면 depth+1
                if(map[nextRY][nextRX] == 'O'){
                    return cur.depth+1;
                }

                // 빨강 파랑 위치가 같으면 움직인 거리에 따라 위치 조정
                if(nextRY == nextBY && nextRX == nextBX){
                    int rMove = Math.abs(nextRY-cur.ry) + Math.abs(nextRX-cur.rx);
                    int bMove = Math.abs(nextBY-cur.by) + Math.abs(nextBX-cur.bx);
                    if(rMove > bMove){
                        nextRY -= dy[d];
                        nextRX -= dx[d];
                    } else if(rMove < bMove){
                        nextBY -= dy[d];
                        nextBX -= dx[d];
                    }
                }

                // 다음 위치에 방문한 적 없다면, 큐에 넣고 방문표시
                if(!visited[nextRY][nextRX][nextBY][nextBX]){
                    q.offer(new RedBlue(nextRY, nextRX, nextBY, nextBX, cur.depth+1));
                    visited[nextRY][nextRX][nextBY][nextBX] = true;
                }
            }
        }
        return -1;
    }
}