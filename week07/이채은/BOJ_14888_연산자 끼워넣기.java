import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] nums;
    public static int[] operators;

    public static int min = Integer.MAX_VALUE;
    public static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        operators = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++){
            operators[i] = Integer.parseInt(st.nextToken());
        }

        calculate(1,nums[0]);

        sb.append(max).append('\n').append(min);
        System.out.println(sb.toString());
    }

    public static void calculate(int numIdx, int result){
        if(numIdx == N){
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }

        for(int i = 0; i < 4; i++){
            if(operators[i] != 0){
                int temp = result;
                if(i==0) temp += nums[numIdx];
                else if(i==1) temp -= nums[numIdx];
                else if(i==2) temp *= nums[numIdx];
                else if(i==3) temp /= nums[numIdx];

                operators[i]--;
                calculate(numIdx+1, temp);
                operators[i]++;
            }
        }
    }
}