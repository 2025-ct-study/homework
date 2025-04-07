import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bt(0,map);
        System.out.println(max);
    }

    public static void bt(int cnt, int[][] map){
        if(cnt == 5){
            max = Math.max(max, findMax(map));
            return;
        }

        // 오른쪽
        bt(cnt+1,right(map));
        // 왼쪽
        bt(cnt+1,left(map));
        // 아래쪽
        bt(cnt+1,down(map));
        // 위쪽
        bt(cnt+1,up(map));
    }

    public static int[][] right(int[][] map){
        int[][] newMap = new int[N][N];
        Stack<Integer> stk = new Stack<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] != 0) stk.push(map[i][j]);
            }

            int idx = N-1;
            while(!stk.isEmpty()){
                newMap[i][idx] = stk.pop();
                if(!stk.isEmpty() && newMap[i][idx] == stk.peek()){
                    newMap[i][idx] += stk.pop();
                }
                idx--;
            }
        }
        return newMap;
    }

    public static int[][] left(int[][] map){
        int[][] newMap = new int[N][N];
        Stack<Integer> stk = new Stack<>();
        for(int i = 0; i < N; i++){
            for(int j = N-1; j >= 0; j--){
                if(map[i][j] != 0) stk.push(map[i][j]);
            }

            int idx = 0;
            while(!stk.isEmpty()){
                newMap[i][idx] = stk.pop();
                if(!stk.isEmpty() && newMap[i][idx] == stk.peek()){
                    newMap[i][idx] +=stk.pop();
                }
                idx++;
            }
        }
        return newMap;
    }

    public static int[][] down(int[][] map){
        int[][] newMap = new int[N][N];
        Stack<Integer> stk = new Stack<>();
        for(int j = 0; j < N; j++){
            for(int i = 0; i < N; i++){
                if(map[i][j] != 0) stk.push(map[i][j]);
            }

            int idx = N-1;
            while(!stk.isEmpty()){
                newMap[idx][j] = stk.pop();
                if(!stk.isEmpty() && newMap[idx][j] == stk.peek()){
                    newMap[idx][j] +=stk.pop();
                }
                idx--;
            }
        }
        return newMap;
    }

    public static int[][] up(int[][] map){
        int[][] newMap = new int[N][N];
        Stack<Integer> stk = new Stack<>();
        for(int j = 0; j < N; j++){
            for(int i = N-1; i >= 0; i--){
                if(map[i][j] != 0) stk.push(map[i][j]);
            }

            int idx = 0;
            while(!stk.isEmpty()){
                newMap[idx][j] = stk.pop();
                if(!stk.isEmpty() && newMap[idx][j] == stk.peek()){
                    newMap[idx][j] +=stk.pop();
                }
                idx++;
            }
        }
        return newMap;
    }

    public static int findMax(int[][] map){
        int maxNum = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                maxNum = Math.max(maxNum, map[i][j]);
            }
        }
        return maxNum;
    }
}