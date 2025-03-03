import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+k-1];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if(i < k-1) arr[i+N] = arr[i];
        }

        // 쿠폰 추가
        int[] eaten = new int[d+1];
        eaten[c] = 1;

        // 윈도우 초기화
        int cnt = 1;
        for(int i = 0; i < k; i++){
            if(eaten[arr[i]] == 0) cnt++;
            eaten[arr[i]]++;
        }

        // 슬라이딩 윈도우
        int max = cnt;
        for(int i = 1; i < N; i++){
            // 앞 제거
            eaten[arr[i-1]]--;
            if(eaten[arr[i-1]] == 0) cnt--;

            // 뒤 추가
            if(eaten[arr[i+k-1]] == 0) cnt++;
            eaten[arr[i+k-1]]++;
            max = Math.max(max,cnt);
        }

        System.out.println(max);
    }
}