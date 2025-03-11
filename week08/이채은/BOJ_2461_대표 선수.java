import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] classes = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                classes[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(classes[i]);
        }

        int[] pointer = new int[N];
        int minDiff = Integer.MAX_VALUE;
        while(true){
            // 최소, 최대값 구하기
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            int minClass = 0;
            for(int i = 0; i < N; i++){
                if(classes[i][pointer[i]] > max){
                    max = classes[i][pointer[i]];
                }
                if(classes[i][pointer[i]] < min){
                    min = classes[i][pointer[i]];
                    minClass = i;
                }
            }

            // 차이 갱신
            minDiff = Math.min(minDiff, max-min);

            // 최소값 포인터 옮기기
            // 포인터가 범위를 벗어나면 break
            if(++pointer[minClass] == M) break;
        }

        System.out.println(minDiff);
    }
}