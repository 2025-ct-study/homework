class Solution {
    public int solution(int n, int w, int num) {
        int h = n/w+1; // 창고 높이

        // 박스 배치
        int cnt = 1;
        int switchDir = 1;
        int[][] storage = new int[h][w];
        for(int i = 0; i < h; i++){
            if(switchDir == 1){
                for(int j = 0; j < w; j++){
                    storage[i][j] = cnt;
                    cnt++;
                }
            }else{
                for(int j = w-1; j >= 0; j--){
                    storage[i][j] = cnt;
                    cnt++;
                }
            }
            switchDir *= -1;
        }

        // 타겟 박스 찾기
        int[] target = new int[2];
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(storage[i][j] == num){
                    target[0] = i;
                    target[1] = j;
                }
            }
        }

        // 꺼내야할 상자 갯수 세기
        int answer = 0;
        for(int i = target[0]; i < h; i++){
            if(storage[i][target[1]] <= n) answer++;
        }

        return answer;
    }
}