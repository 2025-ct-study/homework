import java.util.*;

class Solution {
    int k;
    int n;
    int[][] reqs;
    int[][] waitingTime;

    public int solution(int k, int n, int[][] reqs) {
        this.k = k;
        this.n = n;
        this.reqs = reqs;

        // 각 상담 유형 별 멘토 수 에 따른 기다린 시간
        waitingTime = new int[k+1][n-k+2];
        for(int i = 1; i <= k; i++){
            getWaitingTime(i);
        }

        return getMinTime();
    }

    public void getWaitingTime(int type){
        for(int i = 1; i <= n-k+1; i++){
            int time = 0;
            PriorityQueue<Integer> ongoing = new PriorityQueue<>();
            for(int j = 0; j < reqs.length; j++){
                if(reqs[j][2] != type) continue;

                // 상담원이 있다면
                if(ongoing.size() < i){
                    ongoing.add(reqs[j][0] + reqs[j][1]);
                }
                // 상담원이 없다면
                else{
                    // 제일 빨리 끝나는 멘티 out
                    int fini = ongoing.poll();

                    // 기다려야한다면
                    if(fini > reqs[j][0]){
                        time += fini - reqs[j][0];
                        ongoing.add(fini + reqs[j][1]);
                    }
                    // 안 기다려도 되면
                    else{
                        ongoing.add(reqs[j][0] + reqs[j][1]);
                    }
                }
            }
            waitingTime[type][i] = time;
        }
    }

    public int getMinTime(){
        // 타입별 멘토 수
        int[] mentors = new int[k+1];
        for(int i = 1; i < k+1; i++){
            mentors[i] = 1;
        }

        for(int i = 0; i < n-k; i++){
            int maxDiff = 0;
            int mentorType = 0;
            for(int j = 1; j < k+1; j++){
                int diff = waitingTime[j][mentors[j]] - waitingTime[j][mentors[j]+1];
                if(maxDiff < diff){
                    maxDiff = diff;
                    mentorType = j;
                }
            }
            mentors[mentorType]++;
        }

        int totalWaiting = 0;
        for(int i = 1; i < k+1; i++){
            totalWaiting += waitingTime[i][mentors[i]];
        }
        return totalWaiting;
    }
}