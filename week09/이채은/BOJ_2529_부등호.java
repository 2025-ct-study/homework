import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static char[] compare;
    public static boolean[] numVisited;

    public static long min = Long.MAX_VALUE;
    public static long max = 0;
    public static String[] answer = new String[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        compare = new char[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            compare[i] = st.nextToken().charAt(0);
        }

        numVisited = new boolean[10];
        for(int i = 0; i < 10; i++){
            sb.append(i);
            numVisited[i] = true;
            bt(1, sb);
            sb.deleteCharAt(0);
            numVisited[i] = false;
        }

        System.out.println(answer[0]+"\n"+answer[1]);
    }

    public static void bt(int idx, StringBuilder sb){
        if(idx == N+1){
            long target = Long.parseLong(sb.toString());
            if(target < min){
                min = target;
                answer[1] = sb.toString();
            }
            if(target > max){
                max = target;
                answer[0] = sb.toString();
            }
            return;
        }

        for(int i = 0; i < 10; i++){
            if(numVisited[i]) continue;

            sb.append(i);
            numVisited[i] = true;

            int prevNum = sb.charAt(idx-1)-'0';
            if(compare[idx-1] == '<'){
                if(prevNum < i) bt(idx+1,sb);
            }else{
                if(prevNum > i) bt(idx+1,sb);
            }

            sb.deleteCharAt(sb.length() - 1);
            numVisited[i] = false;
        }
    }
}