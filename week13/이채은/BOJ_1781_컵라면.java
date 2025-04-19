import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] problems = new int[N][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            problems[i][0] = Integer.parseInt(st.nextToken());
            problems[i][1] = Integer.parseInt(st.nextToken());
        }

        // 데드라인 순으로 정렬, 데드라인이 같다면 컵라면 수 순으로 정렬
        Arrays.sort(problems, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]) return Integer.compare(o2[1], o1[1]);
                else return Integer.compare(o1[0], o2[0]);
            }
        });

        int day = 1;
        long ramen = 0;
        for(int i = 0; i < N; i++){
            if(day <= problems[i][0]){
                ramen += problems[i][1];
                day++;
            }
        }

        System.out.println(ramen);
    }
}