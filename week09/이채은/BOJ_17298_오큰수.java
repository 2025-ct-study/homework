import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayDeque<Integer> s = new ArrayDeque<>(); // 오큰수 탐색이 필요한 것들을 스택에 넣음
        for(int i = 0; i < N; i++){
            while(!s.isEmpty() && arr[s.peek()] < arr[i]){
                arr[s.pop()] = arr[i];
            }
            s.push(i);
        }

        while(!s.isEmpty()){
            arr[s.pop()] = -1;
        }

        for(int i = 0; i < N; i++) {
            sb.append(arr[i]).append(" ");
        }

        System.out.println(sb);
    }
}