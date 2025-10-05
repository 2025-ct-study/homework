import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] train = new int[N];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int statementNum = Integer.parseInt(st.nextToken());
            int trainNum = Integer.parseInt(st.nextToken())-1;

            int seatNum = 0;
            if(statementNum == 1){
                seatNum = Integer.parseInt(st.nextToken())-1;
                train[trainNum] |= (1 << seatNum);

            }
            else if(statementNum == 2){
                seatNum = Integer.parseInt(st.nextToken())-1;
                train[trainNum] &= ~(1 << seatNum);
            }
            else if(statementNum == 3){
                train[trainNum] <<= 1;
                train[trainNum] &= ~(1 << 20);
            }
            else if(statementNum == 4){
                train[trainNum] >>= 1;
            }
        }

        System.out.println(countPassedTrain(train));
    }

    public static int countPassedTrain(int[] train){
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < train.length; i++){
            set.add(train[i]);
        }
        return set.size();
    }
}