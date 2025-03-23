import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        int left = 0, right = 0, curK = 0;
        while(right < N){
            while(curK <= K && right < N){
                if(arr[right] % 2 != 0) curK++;
                right++;
            }
            max = Math.max(max, right - left - curK);
            if(arr[left] % 2 != 0) curK--;
            left++;
        }

        System.out.println(max);
    }
}