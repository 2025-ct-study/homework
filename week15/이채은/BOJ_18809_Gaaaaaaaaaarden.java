import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int M;
    public static int G;
    public static int R;
    public static int[][] map;
    public static List<Pos> starter;
    public static int[] started;

    public static int[] dy = {1, -1, 0, 0};
    public static int[] dx = {0, 0, 1, -1};

    public static int max = 0;

    public static class Pos{
        int y,x,color;
        public Pos(int y, int x, int color){
            this.y = y;
            this.x = x;
            this.color = color;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 맵 & 배양액을 뿌릴 수 있는 땅 저장
        map = new int[N][M];
        starter = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) starter.add(new Pos(i,j,0));
            }
        }

        // 빨강 배양액 경우의 수 (1로 표시) -> 초록 배양액 경우의 수 (2로 표시) -> 베양 시작
        started = new int[starter.size()];
        btRED(0,0);

        System.out.println(max);
    }

    public static void btRED(int idx, int cnt){
        if(cnt == R){
            btGREEN(0,0);
            return;
        }

        for(int i = idx; i < started.length; i++){
            started[i] = 1;
            btRED(i+1, cnt+1);
            started[i] = 0;
        }
    }

    public static void btGREEN(int idx, int cnt){
        if(cnt == G){
            bfs();
            return;
        }

        for(int i = idx; i < started.length; i++){
            if(started[i] != 0) continue;
            started[i] = 2;
            btGREEN(i+1, cnt+1);
            started[i] = 0;
        }
    }

    public static void bfs(){
        // 빨강, 초록 배양액 표시
        Queue<Pos> q = new ArrayDeque<>();

        int[][] redTime = new int[N][M];
        for(int i = 0; i < started.length; i++){
            if(started[i] == 1){
                Pos pos = starter.get(i);
                pos.color = 1;
                q.offer(pos);

                redTime[pos.y][pos.x] = 1;
            }
        }

        int[][] greenTime = new int[N][M];
        for(int i = 0; i < started.length; i++){
            if(started[i] == 2){
                Pos pos = starter.get(i);
                pos.color = 2;
                q.offer(pos);

                greenTime[pos.y][pos.x] = 1;
            }
        }

        // 배양 시작
        int cnt = 0;
        while(!q.isEmpty()){
            Pos cur = q.poll();

            if(redTime[cur.y][cur.x] == greenTime[cur.y][cur.x]) continue;

            for(int d = 0; d < 4; d++){
                int nextY = cur.y + dy[d];
                int nextX = cur.x + dx[d];
                if(!isValid(nextY, nextX)) continue;

                if(cur.color == 1){ // 1 = 빨강일때
                    if(redTime[nextY][nextX] == 0 && greenTime[nextY][nextX] == 0){
                        redTime[nextY][nextX] = redTime[cur.y][cur.x] + 1;
                        q.offer(new Pos(nextY,nextX,1));
                    }
                }

                if(cur.color == 2) { // 2 = 초록일때
                    if (greenTime[nextY][nextX] == 0){
                        if (redTime[nextY][nextX] == 0) {
                            greenTime[nextY][nextX] = greenTime[cur.y][cur.x] + 1;
                            q.offer(new Pos(nextY, nextX, 2));
                        } else if (greenTime[cur.y][cur.x] + 1 == redTime[nextY][nextX]) {
                            greenTime[nextY][nextX] = greenTime[cur.y][cur.x] + 1;
                            cnt++;
                        }
                    }
                }
            }
        }
        max = Math.max(cnt, max);
    }

    public static boolean isValid(int y, int x){
        if(y >= 0 && x >= 0 && y < N && x < M && map[y][x] != 0) return true;
        else return false;
    }
}