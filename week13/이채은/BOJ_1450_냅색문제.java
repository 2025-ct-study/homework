import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int C;
    public static int[] items;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        items = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            items[i] = Integer.parseInt(st.nextToken());
        }

        // 중간에서 만나기
        ArrayList<Integer> comb1 = new ArrayList<>();
        bt(0, 0, N/2, comb1);

        ArrayList<Integer> comb2 = new ArrayList<>();
        bt(0, N/2, N, comb2);

        // 정렬
        Collections.sort(comb1);
        Collections.sort(comb2);

        // 이분 탐색
        int cnt = 0;
        for(int i = 0; i < comb1.size(); i++){
            cnt += binarySearch(C-comb1.get(i), comb2); // comb1 + comb2 > C 를 만드는 최소 인덱스
        }

        System.out.println(cnt);
    }

    public static void bt(long sum, int cur, int end, ArrayList<Integer> comb){
        if(cur == end){
            if(sum <= C) comb.add((int)sum);
            return;
        }

        bt(sum, cur+1, end, comb);
        bt(sum+items[cur], cur+1, end, comb);
    }

    public static int binarySearch(int target, ArrayList<Integer> comb){
        int left = 0;
        int right = comb.size()-1;
        while(left <= right){
            int mid = (left + right) / 2;

            if(comb.get(mid) <= target) left = mid + 1;
            else right = mid - 1;
        }

        return left;
    }
}