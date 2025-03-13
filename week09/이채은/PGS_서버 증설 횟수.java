import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;

        Queue<Integer> servers = new ArrayDeque<>(); // 서버가 끝나는 시간 넣기
        for(int i = 0; i < 24; i++){
            // 이용시간 끝난 서버 다 종료 while
            while(!servers.isEmpty() && servers.peek() == i){
                servers.poll();
            }

            // 이용자 수 대비 모자란 서버 갯수 파악
            int addServer = players[i]/m - servers.size();

            // 모자라면 모자란 갯수만큼 증설하기, answer++
            for(int j = 0; j < addServer; j++){
                servers.offer(i+k);
                answer++;
            }
        }

        return answer;
    }
}