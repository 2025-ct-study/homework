import java.io.*;
import java.util.regex.Pattern;

public class Main {
    public static int N;
    public static int K;
    public static String[] nums;
    public static int[][] mods;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 인풋
        N = Integer.parseInt(br.readLine());
        nums = new String[N];
        for(int i = 0; i < N; i++){
            nums[i] = br.readLine();
        }
        K = Integer.parseInt(br.readLine());

        // 기존 나머지 j에 l번째 수를 합친 수의 나머지 저장
        // -1로 초기화 후 아래에서 필요한 경우만 계산
        mods = new int[K][N];
        for(int k = 0; k < K; k++){
            for(int n = 0; n < N; n++){
                mods[k][n] = -1;
            }
        }

        // 비트마스킹 + dp
        long[][] dp = new long[1<<N][K];
        dp[0][0] = 1;
        for(int i = 0; i < (1<<N); i++){ // i 집합에
            for(int l = 0; l < N; l++){ // l 번째 숫자 추가
                if((i & (1<<l)) != 0) continue;

                for(int j = 0; j < K; j++){
                    if(dp[i][j] == 0) continue;

                    // i집합에 l번째 수를 추가한 새로운 집합
                    // 기존 나머지와 l번째 수를 합친 수의 나머지
                    int newComb = i | (1 << l);
                    int newMod = getMod(j, l);
                    dp[newComb][newMod] += dp[i][j];
                }
            }
        }

        long cnt = dp[(1<<N)-1][0];
        long totalCnt = 1;
        for(int i = 1; i <= N; i++){
            totalCnt *= i;
        }

        long gcd = gcd(cnt, totalCnt);
        System.out.println((cnt/gcd) + "/" + (totalCnt/gcd));
    }

    public static int getMod(int j, int l){
        if(mods[j][l] != -1) return mods[j][l];

        String num = nums[l];
        int mod = j;
        for(int i = 0; i < num.length(); i++){
            mod = (mod*10 + (num.charAt(i)-'0')) % K;
        }
        mods[j][l] = mod;
        return mod;
    }

    public static long gcd(long a, long b){
        if(b == 0) return a;
        else return gcd(b, a%b);
    }
}