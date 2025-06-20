class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        for(int i = 0; i < schedules.length; i++){
            // 출근 인정 시간 계산
            int hour = schedules[i]/100;
            int minute = schedules[i]%100 + 10;
            if(minute >= 60){
                hour += 1;
                minute -= 60;
            }

            int accept = hour*100 + minute;

            // 평일에 늦지 않고 출근했는지 판단
            boolean pass = true;
            for(int j = 0; j < 7; j++){
                int day = (startday+j) % 7;
                if(day == 6 || day == 0) continue;

                if(timelogs[i][j] > accept){
                    pass = false;
                    break;
                }
            }
            if(pass) answer++;
        }

        return answer;
    }
}