import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int L;
    public static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        for(int i = 0; i < N; i++){
            if(checkRow(i)) cnt++;
            if(checkCol(i)) cnt++;
        }
        System.out.println(cnt);
    }

    public static boolean checkRow(int idx){
        boolean[] isPut = new boolean[N];
        for(int i = 1; i < N; i++){
            if(Math.abs(map[idx][i] - map[idx][i-1]) >= 2) return false;

            if(map[idx][i] > map[idx][i-1]){
                for(int j = 0; j < L; j++){
                    if(i-1-j < 0 || map[idx][i-1] != map[idx][i-1-j] || isPut[i-1-j]) return false;
                    isPut[i-1-j] = true;
                }
            }
            else if(map[idx][i] < map[idx][i-1]){
                for(int j = 0; j < L; j++){
                    if(i+j >= N || map[idx][i] != map[idx][i+j] || isPut[i+j]) return false;
                    isPut[i+j] = true;
                }
            }
        }
        return true;
    }

    public static boolean checkCol(int idx){
        boolean[] isPut = new boolean[N];
        for(int i = 1; i < N; i++){
            if(Math.abs(map[i][idx] - map[i-1][idx]) >= 2) return false;

            if(map[i][idx] > map[i-1][idx]){
                for(int j = 0; j < L; j++){
                    if(i-1-j < 0 || map[i-1][idx] != map[i-1-j][idx] || isPut[i-1-j]) return false;
                    isPut[i-1-j] = true;
                }
            }
            else if(map[i][idx] < map[i-1][idx]){
                for(int j = 0; j < L; j++){
                    if(i+j >= N || map[i][idx] != map[i+j][idx] || isPut[i+j]) return false;
                    isPut[i+j] = true;
                }
            }
        }
        return true;
    }
}