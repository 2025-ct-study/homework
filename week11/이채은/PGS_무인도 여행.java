import java.util.*;

class Solution {
    int N, M;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};

    public int[] solution(String[] maps) {
        ArrayList<Integer> answer = new ArrayList<>();

        N = maps.length;
        M = maps[0].length();
        boolean[][] visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(maps[i].charAt(j) == 'X' || visited[i][j]) continue;
                int sum = 0;

                // bfs
                Queue<int[]> q = new ArrayDeque<>();
                q.offer(new int[]{i,j});
                visited[i][j] = true;
                while(!q.isEmpty()){
                    int[] cur = q.poll();
                    sum += maps[cur[0]].charAt(cur[1]) - '0';

                    for(int d = 0; d < 4; d++){
                        int nextY = cur[0] + dy[d];
                        int nextX = cur[1] + dx[d];
                        if(!isValid(nextY, nextX) || visited[nextY][nextX]
                                || maps[nextY].charAt(nextX) == 'X' ) continue;

                        q.offer(new int[]{nextY,nextX});
                        visited[nextY][nextX] = true;
                    }
                }
                answer.add(sum);
            }
        }
        if(answer.size() == 0) answer.add(-1);

        return answer.stream().sorted().mapToInt(i->i).toArray();
    }

    public boolean isValid(int y, int x){
        if(y < N && y >= 0 && x < M && x >= 0) return true;
        else return false;
    }
}