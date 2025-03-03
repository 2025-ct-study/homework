import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int[] answer = new int[2];
        int absMin = Integer.MAX_VALUE;

        int left = 0, right = N-1;
        while(left < right){
            int sum = arr[left]+arr[right];
            if(Math.abs(sum) < absMin){
                answer[0] = arr[left];
                answer[1] = arr[right];
                absMin = Math.abs(sum);
            }

            if(sum > 0) right--;
            else if(sum < 0) left++;
            else break;
        }

        sb.append(answer[0]).append(" ").append(answer[1]);
        System.out.println(sb.toString());
    }
}