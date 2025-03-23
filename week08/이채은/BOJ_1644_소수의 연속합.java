import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 에라토스테네스의
        // 소수는 false
        boolean[] prime = new boolean[N+1];
        prime[0] = prime[1] = true;
        for(int i = 2; i*i < N+1; i++){
            if(!prime[i]){
                for(int j = i*i; j < N+1; j+=i){
                    prime[j] = true;
                }
            }
        }

        // 소수 list 생성
        ArrayList<Integer> primes = new ArrayList<>();
        for(int i = 0; i < N+1; i++){
            if(!prime[i]) primes.add(i);
        }

        // 투포인터
        int cnt = 0;
        int left = 0, right = 0, sum = 0;
        while(true){
            if(sum > N) sum -= primes.get(left++);
            else if(right == primes.size()) break;
            else sum += primes.get(right++);

            if(sum == N) cnt++;
        }

        System.out.println(cnt);
    }
}