class Solution {
    public int INF = 1200;

    public int solution(int[][] info, int n, int m) {
        // dp 초기화
        int[][] dp = new int[info.length][m]; // i번째 물건까지 훔치고 B의 누적 흔적 수가 j일 때, A의 누적 흔적 수
        for(int i = 0; i < info.length; i++){
            for(int j = 0; j < m; j++){
                dp[i][j] = INF;
            }
        }

        // dp 초기값 세팅 (0번째 물건 훔쳤을 때)
        dp[0][0] = info[0][0]; // 0번째 물건을 A가 훔쳤을 때, A 누적 흔적 수
        if(info[0][1] < m) dp[0][info[0][1]] = 0; // 0번째 물건을 B가 훔쳤을 때, A 누적 흔적 수

        // dp 업데이트
        for(int i = 1; i < info.length; i++){
            for(int j = 0; j < m; j++){
                // i번째 물건을 A가 훔쳤을 때
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + info[i][0]);

                // i번째 물건을 B가 훔쳤을 때
                if(j + info[i][1] < m){
                    dp[i][j+info[i][1]] = Math.min(dp[i][j+info[i][1]], dp[i-1][j]);
                }
            }
        }

        int answer = INF;
        for(int i = 0; i < m; i++){
            answer = Math.min(answer, dp[info.length-1][i]);
        }

        return answer >= n ? -1 : answer;
    }
}