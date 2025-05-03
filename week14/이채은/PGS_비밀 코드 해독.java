class Solution {
    public int n;
    public int m;
    public int[][] q;
    public int[] ans;
    public int answer = 0;

    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.m = q.length;
        this.q = q;
        this.ans = ans;

        // 완전탐색
        combination(0, 1, new int[5]);

        return answer;
    }

    public void combination(int idx, int cur, int[] comb){
        if(idx == 5){
            // m 번의 시도 확인
            for(int i = 0; i < m; i++){
                if(countMatch(comb, q[i]) != ans[i]) return;
            }
            answer++;
            return;
        }

        for(int i = cur; i <= n; i++){
            comb[idx] = i;
            combination(idx+1, i+1, comb);
        }
    }

    public int countMatch(int[] a, int[] b){
        int cnt = 0;

        // match Index
        // - 매칭된 수의 인덱스를 저장
        // - 불필요한 비교를 없애기 위함
        // - 어차피 서로 다른 수가 오름차순 되어있으니
        int mIdx = 0;
        for(int i = 0; i < 5; i++){
            for(int j = mIdx; j < 5; j++){
                if(a[i] == b[j]){
                    cnt++;
                    mIdx = j+1;
                    break;
                }
            }
        }

        return cnt;
    }
}