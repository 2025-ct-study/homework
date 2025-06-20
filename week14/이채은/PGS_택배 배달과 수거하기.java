class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int d = 0;
        int p = 0;
        for(int i = n; i >=1 ; i--){
            d += deliveries[i-1]; // 배달할 개수
            p += pickups[i-1];    // 수거할 개수

            while(d > 0 || p > 0){  // 배달 or 수거가 있으면
                answer += i*2;
                d -= cap;
                p -= cap;
            }
        }

        return answer;
    }
}