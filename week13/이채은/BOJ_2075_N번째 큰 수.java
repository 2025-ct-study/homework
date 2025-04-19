import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        PriorityQueue<Integer> nums = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                nums.add(Integer.parseInt(st.nextToken()));
            }
        }

        int answer = 0;
        for(int i = 0; i < N; i++){
            answer = nums.poll();
        }

        System.out.println(answer);
    }
}