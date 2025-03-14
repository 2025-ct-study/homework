import java.io.*;
import java.util.*;

public class Main {
    public static int N;

    // 방향 우 -> 하 -> 좌 -> 상
    public static int[] dy = {0, 1, 0, -1};
    public static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N
        N = Integer.parseInt(br.readLine());

        // 사과
        int K = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int appleY = Integer.parseInt(st.nextToken())-1;
            int appleX = Integer.parseInt(st.nextToken())-1;
            map[appleY][appleX] = 1;
        }

        // 방향
        int L = Integer.parseInt(br.readLine());
        Queue<int[]> changeTime = new ArrayDeque<>();
        for(int i = 0; i < L; i++){
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            if(st.nextToken().charAt(0) == 'L') {
                changeTime.offer(new int[]{time,-1});
            } else{
                changeTime.offer(new int[]{time,1});
            }
        }

        ArrayDeque<int[]> snake = new ArrayDeque<>(); // First: 머리, Last: 꼬리
        snake.offerFirst(new int[]{0,0});

        int d = 0;
        int time = 0;
        while(true){
            // 현재위치
            int[] cur = snake.peekFirst();

            // 방향 바꿔야하면 바꾸기
            if (!changeTime.isEmpty() && changeTime.peek()[0] == time) {
                d = (d + changeTime.poll()[1] + 4) % 4; // 음수 방지
            }

            // 다음위치
            int nextY = cur[0] + dy[d];
            int nextX = cur[1] + dx[d];

            //다음위치가 invalid하거나 자기 몸이면, break
            if(!isValid(nextY, nextX) || isContained(snake,nextY,nextX)) break;

            //그게 아니면 다음위치로 머리
            snake.offerFirst(new int[]{nextY, nextX});
            //사과가 있으면 사과 없애기, 사과가 없으면 꼬리 없애기
            if(map[nextY][nextX] == 1) map[nextY][nextX] = 0;
            else snake.pollLast();

            time++;
        }

        System.out.println(time+1);
    }

    public static boolean isValid(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < N) return true;
        else return false;
    }

    public static boolean isContained(ArrayDeque<int[]> snake, int y, int x){
        for(int[] arr : snake){
            if(arr[0] == y && arr[1] == x) return true;
        }
        return false;
    }
}
