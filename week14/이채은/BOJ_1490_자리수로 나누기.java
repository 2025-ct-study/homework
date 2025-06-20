import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int num = N;
        boolean[] nums = new boolean[10];
        while(num != 0){
            nums[num % 10] = true;
            num /= 10;
        }

        long phase = 1;
        while(true){
            for(long i = 0; i < phase ; i++){
                long newNum = N * phase + i;

                // N의 0이 아닌 모든 자리수로 나누어떨어지는지 확인
                boolean flag = true;
                for(int j = 1; j < 10; j++){
                    if(!nums[j]) continue;
                    if(newNum % j != 0){
                        flag = false;
                        break;
                    }
                }

                if(flag){
                    System.out.println(newNum);
                    return;
                }
            }
            phase *= 10;
        }

    }
}