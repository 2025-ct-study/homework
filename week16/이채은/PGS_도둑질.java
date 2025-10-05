class Solution {
    public int solution(int[] money) {
        int N = money.length;

        // 첫번째 집 선택 ㅇ
        int[] dp1 = new int[N];
        dp1[0] = money[0];
        dp1[1] = dp1[0];
        for(int i = 2; i < N-1; i++){
            dp1[i] = Math.max(money[i] + dp1[i-2], dp1[i-1]);
        }

        // 첫번째 집 선택 x
        int[] dp2 = new int[N];
        dp2[0] = 0;
        dp2[1] = money[1];
        for(int i = 2; i < N; i++){
            dp2[i] = Math.max(money[i] + dp2[i-2], dp2[i-1]);
        }

        return Math.max(dp1[N-2], dp2[N-1]);
    }
}