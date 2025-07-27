import java.io.*;
import java.util.*;

public class Main {
    public static class Pool implements Comparable<Pool> {
        int start, end;
        public Pool(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Pool o){
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Pool> pools = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            pools.add(new Pool(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(pools);

        int cnt = 0;
        int cursor = 0;
        for(Pool curPool : pools){
            if(cursor > curPool.start) curPool.start = cursor;

            int need = (curPool.end - curPool.start + K - 1) / K;
            cnt += need;
            cursor = curPool.start + need * K;
        }

        System.out.println(cnt);
    }
}