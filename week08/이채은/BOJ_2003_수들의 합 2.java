import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = 0;
        int sum = 0;
        int cnt = 0;
        while(r <= N){
            if(sum < M){
                sum += arr[r++];
            } else{
                sum -= arr[l++];
            }
            if(sum == M) cnt++;
        }

        System.out.println(cnt);
    }
}