import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        while(true){
            st= new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            if(k == 0) break;

            int[] S = new int[k];
            for(int i = 0; i < k; i++){
                S[i] = Integer.parseInt(st.nextToken());
            }

            bt(0, 0, new int[6], k, S, sb);
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public static void bt(int cnt, int idx, int[] arr, int k, int[] S, StringBuilder sb){
        if(cnt == 6){
            for(int i = 0; i < 6; i++){
                sb.append(arr[i]).append(" ");
            }
            sb.append('\n');
            return;
        }

        for(int i = idx; i < k; i++){
            arr[cnt] = S[i];
            bt(cnt+1, i+1, arr, k, S, sb);
        }
    }
}