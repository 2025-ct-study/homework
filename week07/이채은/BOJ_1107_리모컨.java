import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] broken = new boolean[10];

        if(M != 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        int min = Math.abs(N-100);
        for(int i = 0; i < 1000000; i++){
            String num = String.valueOf(i);

            boolean isbroken = false;
            for(int j = 0; j < num.length(); j++){
                if(broken[num.charAt(j)-'0']){
                    isbroken = true;
                    break;
                }
            }

            if(isbroken) continue;
            min = Math.min(min, Math.abs(N-i)+num.length());
        }

        System.out.println(min);
    }
}