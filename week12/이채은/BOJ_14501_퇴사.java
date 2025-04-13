import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] consult = new int[N+2][2];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            consult[i][0] = Integer.parseInt(st.nextToken());
            consult[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+2];
        for(int i = 1; i <= N+1; i++){
            dp[i] = Math.max(dp[i], dp[i-1]);

            int next = i + consult[i][0];
            if(next > N+1) continue;

            dp[next] = Math.max(dp[next], dp[i]+consult[i][1]);
        }

        System.out.println(dp[N+1]);
    }
}