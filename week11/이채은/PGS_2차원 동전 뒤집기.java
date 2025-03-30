class Solution {
    int[][] beginning;
    int[][] target;

    int N;
    int M;
    int answer = Integer.MAX_VALUE;

    public int solution(int[][] beginning, int[][] target) {
        this.beginning = beginning;
        this.target = target;
        this.N = beginning.length;
        this.M = beginning[0].length;

        comb(0, 0);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public void comb(int idx, int reverseCnt){
        if(idx == N){
            // reverse column
            for(int i = 0; i < M; i++){
                if(equalsReverseCol(i)) reverseCnt++;
                else if (!equalsCol(i)) return;
            }

            answer = Math.min(answer,reverseCnt);
            return;
        }

        // idx 번째 행 뒤집기 x
        comb(idx+1,reverseCnt);

        // idx 번째 행 뒤집기 ㅇ
        reverseRow(idx);
        comb(idx+1,reverseCnt+1);
        reverseRow(idx);
    }

    public void reverseRow(int r){
        for(int i = 0; i < M; i++){
            beginning[r][i] = beginning[r][i] ^ 1;
        }
    }

    public boolean equalsReverseCol(int c){
        for(int i = 0; i < N; i++){
            if(beginning[i][c] == target[i][c]) return false;
        }
        return true;
    }

    public boolean equalsCol(int c){
        for(int i = 0; i < N; i++){
            if(beginning[i][c] != target[i][c]) return false;
        }
        return true;
    }
}