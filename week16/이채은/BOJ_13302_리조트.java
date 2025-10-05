import java.io.*;
import java.util.*;

public class Main {
    public static final int ONE_DAY = 10000;
    public static final int THREE_DAY = 25000;
    public static final int FIVE_DAY = 37000;
    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        if(M != 0) st = new StringTokenizer(br.readLine());

        boolean[] impossible = new boolean[N+1];
        for(int i = 0; i < M; i++){
            impossible[Integer.parseInt(st.nextToken())] = true;
        }

        int[][] dp = new int[N+5][50];
        for (int i = 0; i < N+5; i++) Arrays.fill(dp[i], INF);

        dp[0][0] = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 0; j <= 40; j++){
                int prev = dp[i-1][j];

                // 아무 갱신이 없었던 경우 pass
                if(prev == INF) continue;

                // 불가능한 날은 전날 요금 이월
                if(impossible[i]){
                    dp[i][j] = Math.min(dp[i][j], prev);
                    continue;
                }

                // 쿠폰을 사용한다면
                if(j >= 3) dp[i][j-3] = Math.min(dp[i][j-3], prev);

                // 1일
                dp[i][j] = Math.min(dp[i][j], prev+ONE_DAY);

                // 3일
                for(int k = 0; k < 3; k++) dp[i+k][j+1] = Math.min(dp[i+k][j+1], prev+THREE_DAY);

                // 5일
                for(int k = 0; k < 5; k++) dp[i+k][j+2] = Math.min(dp[i+k][j+2], prev+FIVE_DAY);
            }
        }

        int min = INF;
        for(int i = 0; i <= 40; i++){
            min = Math.min(min, dp[N][i]);
        }

        System.out.println(min);
    }
}