import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // dp를 통해 N길이의 타일을 채우는 모든 경우의 수를 구함
        // dp[i] = i 길이에서 1을 뺀 경우의 수 + i 길이에서 2를 뺀 경우의 수 * 2
        int[] dp = new int[N+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= N; i++){
            dp[i] = dp[i-1] + dp[i-2]*2;
        }

        // 뒤집어서 좌우 대칭인 경우는 한가지로 경우로 처리
        // 'N길이의 타일을 채우는 모든 경우의 수 = 좌우대칭인 경우 + 2 * 뒤집어서 좌우대칭인 경우' 로 나타낼 수 있다.
        // 따라서 모든 경우의 수 dp[N]에 좌우대칭인 경우를 더하여, '2 * 좌우대칭인 경우 + 2 * 뒤집어서 좌우대칭인 경우' 로 만들어준다.
        // 이를 2로 나누면 '좌우대칭인 경우 + 뒤집어서 좌우대칭인 경우', 즉 중복을 제거한 전체 경우의 수가 된다.

        // 좌우대칭인 경우의 수는 N이 홀수이냐 짝수이냐에 따라 다르게 구한다.
        // N 이 홀수인 경우: 가운데 가로 길이 1인 타일이 있는 경우 이므로 dp[N/2]
        // N 이 짝수인 경우: 완전히 좌우 대칭인 경우 dp[N/2] + 가운데 가로 길이 2인 타일이 있는 경우 dp[(N-2)/2]*2
        int answer = 0;
        if(N % 2 != 0) System.out.println( (dp[N] + dp[N/2])/2 );
        else System.out.println( (dp[N] + dp[N/2] + dp[(N-2)/2]*2)/2 );
    }
}