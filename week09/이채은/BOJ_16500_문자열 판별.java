import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int N = Integer.parseInt(br.readLine());

        Set<String> words = new HashSet<>();
        for(int i = 0; i < N; i++){
            words.add(br.readLine());
        }

        int sLength = S.length();
        int[] dp = new int[sLength+1];
        dp[sLength] = 1;
        for(int i = sLength-1; i >=0; i--){
            for(int j = i+1; j < sLength+1; j++){
                if(dp[j] == 1){
                    if(words.contains(S.substring(i,j))){
                        dp[i] = 1;
                        break;
                    }
                }
            }
        }

        System.out.println(dp[0]);
    }
}