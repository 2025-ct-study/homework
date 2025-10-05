import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[] ip = new int[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(),".");
            for(int j = 0; j < 4; j++){
                ip[i] <<= 8;
                ip[i] |= Integer.parseInt(st.nextToken());
            }
        }

        // mask 구하기: 비트 별 같은지 비교 -> 같으면 1, 다른 거 나오면 그 이후는 모두 0
        int mask = 0;
        boolean flag = false;
        for(int i = 31; i >= 0; i--){
            int bit = 1 << i;
            for(int j = 1; j < N; j++){
                if((ip[0] & bit) != (ip[j] & bit)){
                    flag = true;
                    break;
                }
            }

            if(flag) break;
            else mask |= bit;
        }

        // address 구하기: ip와 mask의 교집합
        int address = ip[0] & mask;

        printNetwork(address);
        printNetwork(mask);
    }

    public static void printNetwork(int network){
        String network32 = String.format("%32s", Integer.toBinaryString(network)).replace(' ', '0');

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4; i++){
            sb.append(Integer.parseInt(network32.substring(i * 8, (i + 1) * 8),2));
            if(i != 3) sb.append(".");
        }

        System.out.println(sb);
    }
}