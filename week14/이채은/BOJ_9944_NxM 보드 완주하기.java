import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int M;
    public static char[][] map;
    public static boolean[][] visited;
    public static int blankCnt;
    public static int minStep;

    public static int[] dy = {1, -1, 0, 0};
    public static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = 1;
        String NM = br.readLine();
        while(NM != null){
            StringTokenizer st = new StringTokenizer(NM);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N][M];
            visited = new boolean[N][M];

            blankCnt = 0;
            minStep = Integer.MAX_VALUE;

            // 맵
            for(int i = 0; i < N; i++){
                String line = br.readLine();
                for(int j = 0; j < M; j++){
                    map[i][j] = line.charAt(j);
                    if(map[i][j] == '.') blankCnt++;
                }
            }

            // 각 빈칸을 시작점으로 백트래킹
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(map[i][j] == '*') continue;
                    visited[i][j] = true;
                    bt(i,j,0,1);
                    visited[i][j] = false;
                }
            }

            minStep = (minStep == Integer.MAX_VALUE) ? -1 : minStep;
            sb.append("Case " + tc + ": " + minStep + '\n');

            tc++;
            NM = br.readLine();
        }

        System.out.print(sb);
    }

    public static void bt(int curY, int curX, int step, int moveCnt){
        // 모두 방문하였는지 확인
        if(moveCnt == blankCnt){
            minStep = Math.min(step, minStep);
            return;
        }

        // 방향
        for(int d = 0; d < 4; d++){
            // 유효한 방향인지 확인
            int nextY = curY + dy[d];
            int nextX = curX + dx[d];
            if(!isValid(nextY,nextX) || visited[nextY][nextX]) continue;

            // 이동
            int moves = 0;
            while(isValid(nextY,nextX) && !visited[nextY][nextX]){
                visited[nextY][nextX] = true;
                nextY += dy[d];
                nextX += dx[d];
                moves++;
            }
            nextY -= dy[d];
            nextX -= dx[d];

            // 다음
            bt(nextY, nextX, step+1, moveCnt+moves);

            // 이동 원상복구
            while(moves > 0){
                visited[nextY][nextX] = false;
                nextY -= dy[d];
                nextX -= dx[d];
                moves--;
            }
        }
    }

    public static boolean isValid(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < M && map[y][x] == '.') return true;
        else return false;
    }
}