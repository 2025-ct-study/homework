import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int K;
    public static int[][] lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        lines = new int[N][2];

        int end = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 2; j++){
                lines[i][j] = Integer.parseInt(st.nextToken());
                end = Math.max(end, lines[i][j]);
            }
        }

        int curK = 0;
        int left = 0, right = 0;
        while(true){
            if(curK < K){
                right++;
                curK += count(right);
            }
            else if(curK > K){
                left++;
                curK -= count(left);
            }
            else break;

            if(right > end){
                left = 0;
                right = 0;
                break;
            }
        }

        sb.append(left).append(" ").append(right);
        System.out.println(sb);
    }

    public static int count(int idx){
        int cnt = 0;
        for(int i = 0; i < N; i++){
            if(lines[i][0] < idx && idx <= lines[i][1]) cnt++;
        }
        return cnt;
    }
}