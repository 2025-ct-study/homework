import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int K;
    public static int M;
    public static Map<Integer,Integer> binaryMap;

    public static int start;
    public static int[] prev;
    public static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        binaryMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            int binary = Integer.parseInt(br.readLine(),2);
            binaryMap.put(binary,i);

            if(i == 1) start = binary;
        }

        prev = new int[N+1];
        for (int i = 1; i <= N; i++) {
            prev[i] = -1;
        }

        // 각 코드 별 최단 경로 구하기 (prev에 이전 코드 저장)
        bfs();

        sb = new StringBuilder();
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            int query = Integer.parseInt(br.readLine());
            getPath(query);
        }

        System.out.print(sb);
    }

    public static void bfs() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);

        prev[1] = 1;
        while (!q.isEmpty()) {
            int curCode = q.poll();
            int curIdx = binaryMap.get(curCode);

            for (int i = 0; i < K; i++) {
                Integer hammingCode = curCode ^ (1 << i); // 해밍코드: 현재 코드와 해밍거리가 1인 이진코드
                Integer nextIdx = binaryMap.get(hammingCode);

                if(nextIdx != null && prev[nextIdx] == -1) {
                    prev[nextIdx] = curIdx;
                    q.add(hammingCode);
                }
            }
        }
    }

    public static void getPath(int cur){
        if(prev[cur] == -1){
            sb.append(-1).append('\n');
            return;
        }

        Stack<Integer> stack = new Stack<>();
        while(true){
            stack.push(cur);
            if(cur == 1) break;
            cur = prev[cur];
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }
        sb.append('\n');
    }
}