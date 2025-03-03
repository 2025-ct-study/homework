import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            int cnt = 0;
            int N = Integer.parseInt(br.readLine());

            int[] services = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                services[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(services);

            // 300 갯수 / 3 = 서버 1개
            int cnt300 = 0;
            for(int i = 0; i < N; i++){
                if(services[i] == 300) cnt300++;
            }

            // 600보다 큰 값 = 서버 1개
            int end = N-1;
            for(int i = 0; i < N; i++){
                if(services[i] > 600){
                    end = i-1;
                    cnt += N-i;
                    break;
                }
            }

            // 300 보다 크고 600 보다 같거나 작은 수에 대해
            int start = cnt300;
            while(start <= end){
                // 합이 900 보다 같거나 작다면 start와 end = 서버 1개
                if(start < end && services[start] + services[end]<= 900) start++;

                    // 합이 900보다 크다면, 남아있는 300이 있다면 end와 300 = 서버 1개, 300이 없으면 end = 서버 1개
                else if (cnt300 > 0) cnt300--;

                end--;
                cnt++;
            }

            // 남은 300에 대해서
            cnt += (cnt300+2)/3;
            sb.append(cnt).append('\n');
        }

        System.out.println(sb.toString());
    }
}
