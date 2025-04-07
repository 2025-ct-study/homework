import java.util.*;

class Solution {
    int N;
    int M;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, -1, 1};

    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;

        // bfs 를 통해 석유 그룹 탐색
        // 그룹번호 지정, 그룹 별 석유량 저장
        int gIdx = 1;
        Map<Integer, Integer> oilGroup = new HashMap<>();

        boolean[][] visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(land[i][j] == 0 || visited[i][j]) continue;
                int oil = 0;

                // bfs
                Queue<int[]> q = new ArrayDeque<>();
                q.offer(new int[]{i,j});
                visited[i][j] = true;
                while(!q.isEmpty()){
                    int[] cur = q.poll();
                    land[cur[0]][cur[1]] = gIdx;
                    oil++;

                    for(int d = 0; d < 4; d++){
                        int ny = cur[0] + dy[d];
                        int nx = cur[1] + dx[d];

                        if(isValid(ny,nx) && land[ny][nx] != 0 && !visited[ny][nx]){
                            q.offer(new int[]{ny,nx});
                            visited[ny][nx] = true;
                        }
                    }
                }
                oilGroup.put(gIdx, oil);
                gIdx++;
            }
        }

        // 각 열을 탐색하며 최대 석유량 찾기
        int maxOilSum = 0;
        for(int j = 0; j < M; j++){
            Set<Integer> oilGroupIdx = new HashSet<>();
            for(int i = 0; i < N; i++){
                if(land[i][j] > 0) oilGroupIdx.add(land[i][j]);
            }

            int oilSum = 0;
            for(int idx: oilGroupIdx){
                oilSum += oilGroup.get(idx);
            }
            maxOilSum = Math.max(maxOilSum, oilSum);
        }

        return maxOilSum;
    }

    public boolean isValid(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < M) return true;
        else return false;
    }
}