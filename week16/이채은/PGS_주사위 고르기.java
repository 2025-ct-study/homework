import java.util.*;

class Solution {

    public int n;
    public int[][] dice;
    public boolean[] diceAB;

    public int max = 0;
    public int[] answer;

    public int[] solution(int[][] dice) {
        this.n = dice.length;
        this.dice = dice;
        this.diceAB = new boolean[n];

        this.answer = new int[n/2];

        // 주사위를 가져가는 모든 경우의 수
        comb(0, 0);

        return answer;
    }

    public void comb(int curIdx, int cnt){
        if(cnt == n/2) {
            // 해당 조합으로 A가 이기는 수
            int num = countAWin();

            // 최댓값 갱신
            if(max < num){
                max = num;

                int j = 0;
                for(int i = 0; i < n; i++){
                    if(diceAB[i]) answer[j++] = i+1;
                }
            }
            return;
        }

        for(int i = curIdx; i < n; i++){
            diceAB[i] = true;
            comb(i+1, cnt+1);
            diceAB[i] = false;
        }
    }

    public int countAWin(){
        int num = 0;

        // A 주사위 모든 합의 경우의 수
        ArrayList<Integer> sumAList = new ArrayList<>();
        sum(0,0,0,sumAList,'A');

        // B 주사위 모든 합의 경우의 수
        ArrayList<Integer> sumBList = new ArrayList<>();
        sum(0,0,0,sumBList,'B');

        // 이진탐색
        Collections.sort(sumBList);
        for(int i = 0; i < sumAList.size(); i++){
            int target = sumAList.get(i);

            int left = 0, right = sumBList.size()-1;
            while(left <= right){
                int mid = (left + right) / 2;

                if(sumBList.get(mid) >= target) right = mid-1;
                else left = mid+1;
            }

            num += left;
        }

        return num;
    }

    public void sum(int sum, int diceCnt, int curIdx, ArrayList<Integer> sumList, char type){
        if(diceCnt == n/2){
            sumList.add(sum);
            return;
        }

        for(int i = curIdx; i < n; i++){
            if(type=='A'&&diceAB[i] || type=='B'&&!diceAB[i]){
                for(int j = 0; j < 6; j++){
                    sum(sum+dice[i][j], diceCnt+1, i+1, sumList, type);
                }
            }
        }
    }
}