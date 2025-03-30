import java.util.*;

class Solution {
    public class Job{
        int num, start, duration;
        public Job(int num, int start, int duration){
            this.num = num;
            this.start = start;
            this.duration = duration;
        }
    }

    public int solution(int[][] jobs) {
        int N = jobs.length;

        // job 정렬 - 시작 시간 순
        ArrayList<Job> jobList = new ArrayList<>();
        for(int i = 0; i < N; i++){
            jobList.add(new Job(i,jobs[i][0],jobs[i][1]));
        }
        Collections.sort(jobList, (o1, o2) -> o1.start-o2.start);

        //우선순위: 작업의 소요시간이 짧은 것, 작업의 요청 시각이 빠른 것, 작업의 번호가 작은 것 순
        PriorityQueue<Job> pq = new PriorityQueue<Job>(new Comparator<Job>(){
            @Override
            public int compare(Job o1, Job o2){
                if(o1.duration == o2.duration){
                    if(o1.start == o2.start) return o1.num - o2.num;
                    else return o1.start - o2.start;
                }
                else return o1.duration - o2.duration;
            }
        });

        int curJob = 0;
        int curTime = 0;
        int execute = 0;
        int turnaround = 0;
        while(execute < N){
            // 요청들어온 작업 대기큐에 넣기
            while(curJob < N && jobList.get(curJob).start <= curTime){
                pq.offer(jobList.get(curJob));
                curJob++;
            }

            // 우선순위 높은 작업 수행
            if(pq.isEmpty()){
                curTime++;
            }
            else{
                Job job = pq.poll();
                curTime += job.duration;
                turnaround += (curTime-job.start);
                execute++;
                System.out.println(job.num);
            }
        }

        return turnaround / N;
    }
}