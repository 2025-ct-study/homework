import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int M;
    public static ArrayList<int[]> cctvs;
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cctvs = new ArrayList<>();
        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] <= 5) cctvs.add(new int[]{i,j,map[i][j]});
            }
        }

        runCCTV(0, map);
        System.out.println(min);
    }

    public static void runCCTV(int cctvNum, int[][] map){
        if(cctvNum == cctvs.size()){
            min = Math.min(min, getZero(map));
            return;
        }

        int[] cctv = cctvs.get(cctvNum);

        int[][] tmp;
        if(cctv[2] == 1) {
            tmp = copyMap(map);
            watchUp(cctv[0], cctv[1], tmp);
            runCCTV(cctvNum+1, tmp);

            tmp = copyMap(map);
            watchDown(cctv[0], cctv[1], tmp);
            runCCTV(cctvNum+1, tmp);

            tmp = copyMap(map);
            watchLeft(cctv[0], cctv[1], tmp);
            runCCTV(cctvNum+1, tmp);

            tmp = copyMap(map);
            watchRight(cctv[0], cctv[1], tmp);
            runCCTV(cctvNum+1, tmp);
        }
        else if(cctv[2] == 2){
            tmp = copyMap(map);
            watchUp(cctv[0], cctv[1], tmp);
            watchDown(cctv[0], cctv[1], tmp);
            runCCTV(cctvNum+1, tmp);

            tmp = copyMap(map);
            watchLeft(cctv[0], cctv[1], tmp);
            watchRight(cctv[0], cctv[1], tmp);
            runCCTV(cctvNum+1, tmp);
        }
        else if(cctv[2] == 3){
            tmp = copyMap(map);
            watchUp(cctv[0], cctv[1], tmp);
            watchRight(cctv[0], cctv[1], tmp);
            runCCTV(cctvNum+1, tmp);

            tmp = copyMap(map);
            watchRight(cctv[0], cctv[1], tmp);
            watchDown(cctv[0], cctv[1], tmp);
            runCCTV(cctvNum+1, tmp);

            tmp = copyMap(map);
            watchDown(cctv[0], cctv[1], tmp);
            watchLeft(cctv[0], cctv[1], tmp);
            runCCTV(cctvNum+1, tmp);

            tmp = copyMap(map);
            watchLeft(cctv[0], cctv[1], tmp);
            watchUp(cctv[0], cctv[1], tmp);
            runCCTV(cctvNum+1, tmp);
        }
        else if(cctv[2] == 4){
            tmp = copyMap(map);
            watchLeft(cctv[0], cctv[1], tmp);
            watchUp(cctv[0], cctv[1], tmp);
            watchRight(cctv[0], cctv[1], tmp);
            runCCTV(cctvNum+1, tmp);

            tmp = copyMap(map);
            watchUp(cctv[0], cctv[1], tmp);
            watchRight(cctv[0], cctv[1], tmp);
            watchDown(cctv[0], cctv[1], tmp);
            runCCTV(cctvNum+1, tmp);

            tmp = copyMap(map);
            watchRight(cctv[0], cctv[1], tmp);
            watchDown(cctv[0], cctv[1], tmp);
            watchLeft(cctv[0], cctv[1], tmp);
            runCCTV(cctvNum+1, tmp);

            tmp = copyMap(map);
            watchDown(cctv[0], cctv[1], tmp);
            watchLeft(cctv[0], cctv[1], tmp);
            watchUp(cctv[0], cctv[1], tmp);
            runCCTV(cctvNum+1, tmp);
        }
        else if(cctv[2] == 5){
            tmp = copyMap(map);
            watchUp(cctv[0], cctv[1], tmp);
            watchDown(cctv[0], cctv[1], tmp);
            watchLeft(cctv[0], cctv[1], tmp);
            watchRight(cctv[0], cctv[1], tmp);
            runCCTV(cctvNum+1, tmp);
        }
    }

    public static int getZero(int[][] map){
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    public static int[][] copyMap(int[][] map){
        int[][] newMap = new int[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }

    public static void watchUp(int y, int x, int[][] map){
        for(int i = y-1; i >= 0; i--){
            if(map[i][x] == 6) break;
            map[i][x] = -1;
        }
    }

    public static void watchDown(int y, int x, int[][] map){
        for(int i = y+1; i < N; i++){
            if(map[i][x] == 6) break;
            map[i][x] = -1;
        }
    }

    public static void watchLeft(int y, int x, int[][] map){
        for(int i = x-1; i >= 0; i--){
            if(map[y][i] == 6) break;
            map[y][i] = -1;
        }
    }

    public static void watchRight(int y, int x, int[][] map){
        for(int i = x+1; i < M; i++){
            if(map[y][i] == 6) break;
            map[y][i] = -1;
        }
    }
}