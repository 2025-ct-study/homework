class Solution {
    int M,N;
    int[][] key;
    int[][] lock;

    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        this.key = key;
        this.lock = lock;

        // key를 시계 방향 90도씩 회전 (4방향에 대해)
        for(int d = 0; d < 4; d++){
            rotate();
            // 이동하기
            for(int i = -M+1; i < N; i++){
                for(int j = -M+1; j < N; j++){
                    // key 시작점 i,j에 대해 열 수 있는지
                    if(isOpened(i,j)) return true;
                }
            }
        }
        return false;
    }

    public void rotate(){
        int[][] temp = new int[M][M];
        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                temp[i][j] = key[i][j];
            }
        }

        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                key[i][j] = temp[j][M-1-i];
            }
        }
    }

    public boolean isOpened(int keyY, int keyX){
        int keyY_start = keyY < 0 ? 0 : keyY;
        int keyX_start = keyX < 0 ? 0 : keyX;
        int keyY_end = keyY+M > N ? N : keyY+M;
        int keyX_end = keyX+M > N ? N : keyX+M;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                // 키와 겹쳐있다면
                if(i >= keyY_start && j >= keyX_start && i < keyY_end && j < keyX_end){
                    if((lock[i][j] == 0 && key[i-keyY][j-keyX] == 0)
                            || (lock[i][j] == 1 && key[i-keyY][j-keyX] == 1)) return false;
                }
                // 키와 겹쳐있지 않다면
                else{
                    if(lock[i][j] == 0) return false;
                }
            }
        }
        return true;
    }
}