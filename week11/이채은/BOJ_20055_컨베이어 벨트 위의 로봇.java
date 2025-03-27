import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] conveyor = new int[N*2];
        boolean[] robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2*N; i++){
            conveyor[i] = Integer.parseInt(st.nextToken());
        }

        int step = 0;
        while(K > 0){
            step++;
            // 1. 컨베이어 한칸 회전
            int temp = conveyor[2*N-1];
            for(int i = N*2-1; i >= 1; i--){
                conveyor[i] = conveyor[i-1];
                if(i < N-1){
                    robot[i] = robot[i-1];
                }
            }
            conveyor[0] = temp;
            robot[0] = false;

            // 2. 로봇 이동할 수 있는 경우에 이동 + 내구도 감소
            for(int i = N-1; i >= 1; i--){
                if(robot[i-1] && !robot[i] && conveyor[i] > 0){
                    robot[i-1] = false;
                    robot[i] = true;
                    if(--conveyor[i] == 0) K--;
                }
            }
            robot[N-1] = false;

            // 3. 로봇 올릴 수 있으면 올림
            if(!robot[0] && conveyor[0] > 0){
                robot[0] = true;
                if(--conveyor[0] == 0) K--;
            }
        }

        System.out.println(step);
    }
}