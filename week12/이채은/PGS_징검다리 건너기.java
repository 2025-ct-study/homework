import java.util.*;

class Solution {
    int N;
    int[] stones;

    public int solution(int[] stones, int k) {
        N = stones.length;
        this.stones = stones;

        // 디딤돌 복사하여 정렬
        int[] sortedStones = new int[N];
        for(int i = 0; i < N; i++){
            sortedStones[i] = stones[i];
        }
        Arrays.sort(sortedStones);

        // 이분탐색
        int l = 0;
        int r = N-1;
        while(l <= r){
            int mid = (l+r)/2;

            if(getMaxSkip(sortedStones[mid]) > k) r = mid-1;
            else l = mid+1;
        }

        return l == N ? sortedStones[l-1] : sortedStones[l];
    }

    public int getMaxSkip(int target){
        int max = 0;
        int cnt = 0;
        for(int i = 0; i < N; i++){
            if(stones[i]  <= target){
                cnt++;
                max = Math.max(max, cnt);
            }
            else cnt = 0;
        }

        return max+1;
    }
}