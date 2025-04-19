import java.io.*;
import java.util.*;

public class Main {
    public static int R;
    public static int C;
    public static int M;

    public static int[][] map;
    public static Shark[] sharks;

    public static int[] dR = {-1, 1, 0, 0};
    public static int[] dC = {0, 0, 1, -1};

    public static class Shark{
        int speed, direction, size;

        public Shark(int speed, int direction, int size){
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        sharks = new Shark[M+1];

        int r,c,s,d,z;
        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());

            r = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken())-1;
            z = Integer.parseInt(st.nextToken());

            map[r][c] = i; // 상어위치에 상어번호 저장
            sharks[i] = new Shark(s,d,z); // 상어 정보 저장
        }

        int pos = -1;
        int cnt = 0;
        while(true){
            // 낚시왕 이동
            if(++pos == C) break;

            // 낚시왕 낚시
            cnt += fish(pos);

            // 상어 이동
            moveShark();
        }

        System.out.println(cnt);
    }

    public static int fish(int pos){
        int sharkNum;
        for(int i = 0; i < R; i++){
            if(map[i][pos] != 0){
                sharkNum = map[i][pos];
                map[i][pos] = 0;
                return sharks[sharkNum].size;
            }
        }
        return 0;
    }

    public static void moveShark(){
        int[][] tempMap = new int[R][C];

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] != 0) {
                    Shark shark = sharks[map[i][j]];

                    // 상어의 다음 이동 위치
                    int speed = shark.speed;
                    if(shark.direction == 0 || shark.direction == 1)
                        speed %= (R-1) * 2;
                    else if(shark.direction == 2 || shark.direction == 3)
                        speed %= (C-1) * 2;

                    int nextR = i;
                    int nextC = j;
                    for(int s = 0; s < speed; s++){
                        nextR += dR[shark.direction];
                        nextC += dC[shark.direction];

                        if(nextR < 0 || nextC < 0 || nextR >= R || nextC >= C){
                            if(shark.direction == 0) shark.direction = 1;
                            else if(shark.direction == 1) shark.direction = 0;
                            else if(shark.direction == 2) shark.direction = 3;
                            else if(shark.direction == 3) shark.direction = 2;
                            nextR += dR[shark.direction]*2;
                            nextC += dC[shark.direction]*2;
                        }
                    }

                    // 한 위치에 상어가 두마리 이상일 땐 큰 상어가 이김
                    if(tempMap[nextR][nextC] == 0){
                        tempMap[nextR][nextC] = map[i][j];
                    }
                    else{
                        Shark originShark = sharks[tempMap[nextR][nextC]];
                        if(originShark.size < shark.size) tempMap[nextR][nextC] = map[i][j];
                    }
                }
            }
        }

        map = tempMap;
    }
}