import java.io.*;
import java.util.*;

public class Main {
    public static int R;
    public static int C;
    public static int[] dy = {1, -1, 0, 0};
    public static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int mDown = 0;
        int[][] room = new int[R][C];
        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++){
                room[i][j] = Integer.parseInt(st.nextToken());
                if(room[i][j] == -1) mDown = i;
            }
        }

        while(T > 0){
            moveDust(room); // 1. 미세먼지 확산
            runMachine(room,mDown); // 2. 공기청정기 작동
            T--;
        }

        System.out.println(countDust(room));
    }

    public static void moveDust(int[][] room){
        // 이중확산 방지를 위해, 우선 확산 양 계산 후 한꺼번에 확산
        // 미세먼지 확산 양 계산
        int[][] dust = new int[R][C];
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(room[i][j] <= 0) continue;

                int diffusion = room[i][j]/5;
                int diffusionCnt = 0;
                for(int d = 0; d < 4; d++){
                    int nextY = i + dy[d];
                    int nextX = j + dx[d];
                    if(!isValid(nextY,nextX) || room[nextY][nextX] == -1) continue;

                    dust[nextY][nextX] += diffusion;
                    diffusionCnt++;
                }

                room[i][j] -= diffusion * diffusionCnt;
            }
        }

        // 미세먼지 확산
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                room[i][j] += dust[i][j];
            }
        }
    }

    public static void runMachine(int[][] room, int mDown){
        // 위 공기청정기
        for(int i = mDown-2; i >= 1; i--){
            room[i][0] = room[i-1][0];
        }
        for(int j = 0; j < C-1; j++){
            room[0][j] = room[0][j+1];
        }
        for(int i = 0; i < mDown-1; i++){
            room[i][C-1] = room[i+1][C-1];
        }
        for(int j = C-1; j > 1; j--){
            room[mDown-1][j] = room[mDown-1][j-1];
        }
        room[mDown-1][1] = 0;

        // 아래 공기청정기
        for(int i = mDown+1; i < R-1; i++){
            room[i][0] = room[i+1][0];
        }
        for(int j = 0; j < C-1; j++){
            room[R-1][j] = room[R-1][j+1];
        }
        for(int i = R-1; i > mDown; i--){
            room[i][C-1] = room[i-1][C-1];
        }
        for(int j = C-1; j > 1; j--){
            room[mDown][j] = room[mDown][j-1];
        }
        room[mDown][1] = 0;
    }

    public static int countDust(int[][] room){
        int cnt = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                cnt += room[i][j];
            }
        }
        return cnt+2;
    }

    public static boolean isValid(int y, int x){
        if(y >= 0 && x >= 0 && y < R && x < C) return true;
        else return false;
    }
}