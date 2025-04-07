import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        for(int i = 0; i < N; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }

        // dp
        int[] dp = new int[K+1];
        for(int i = 1; i <= K; i++){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < N; j++){
                if(i-coins[j] < 0 || dp[i-coins[j]] == -1) continue;
                min = Math.min(min, dp[i-coins[j]]);
            }

            if(min == Integer.MAX_VALUE) dp[i] = -1;
            else dp[i] = min+1;
        }

        System.out.println(dp[K]);
    }
}