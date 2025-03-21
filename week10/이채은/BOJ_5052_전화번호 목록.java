import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for(int t = 0; t < tc; t++){
            int n = Integer.parseInt(br.readLine());

            String[] numbers = new String[n];
            for(int i = 0; i < n; i++){
                numbers[i] = br.readLine();
            }
            Arrays.sort(numbers);

            boolean flag = true;
            for(int i = 1; i < n; i++){
                if(numbers[i].startsWith(numbers[i-1])){
                    flag = false;
                    break;
                }
            }
            if(flag) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }
        System.out.println(sb);
    }
}