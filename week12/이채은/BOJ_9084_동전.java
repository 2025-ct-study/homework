import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++){
            int N = Integer.parseInt(br.readLine());

            int[] coins = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                coins[i] = Integer.parseInt(st.nextToken());
            }

            // coin 별로 순차적으로 dp 진행
            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M+1];
            dp[0] = 1;
            for(int i = 0; i < N; i++){
                for(int j = coins[i]; j < M+1; j++){
                    dp[j] = dp[j] + dp[j-coins[i]];
                }
            }

            sb.append(dp[M]).append('\n');
        }

        System.out.println(sb);
    }
}