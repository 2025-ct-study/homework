class Solution {
    public int[][] users;
    public int[] emoticons;
    public int emoticonNum;

    public int[] answer = {0,0}; // 1. 이모티콘 플러스 가입자 최대, 2. 이모티콘 판매액 최대

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        this.emoticonNum = emoticons.length;

        sale(0, new int[emoticonNum]);
        return answer;
    }

    public void sale(int emoIdx, int[] emoDiscount){
        if(emoIdx == emoticonNum){
            // 이모티콘 할인율에 따른 플러스 서비스 가입자와 판매액 계산 => 행사 목적 최대한 달성하도록 업데이트
            buy(emoDiscount);
            return;
        }

        for(int i = 1; i < 5; i++){
            emoDiscount[emoIdx] = i*10;
            sale(emoIdx+1, emoDiscount);
        }
    }

    public void buy(int[] emoDiscount){
        int emoPlus = 0;
        int totalCost = 0;
        for(int i = 0; i < users.length; i++){
            // 기준 할인율 이상의 이모티콘 구매
            int userCost = 0;
            for(int j = 0; j < emoticonNum; j++){
                if(emoDiscount[j] >= users[i][0]){
                    userCost += emoticons[j] * (100-emoDiscount[j]) / 100;
                }
            }
            // 구매가격이 기준 구매액 이상이라면 이모티콘 플러스++, 아니라면 판매액++
            if(userCost >= users[i][1]) emoPlus++;
            else totalCost += userCost;
        }

        // 이모티콘 사람 수가 높다면 업데이트
        // 이모티콘 사람 수가 같다면, 판매액이 높다면, 업데이트
        if(emoPlus > answer[0]){
            answer[0] = emoPlus;
            answer[1] = totalCost;
        } else if(emoPlus == answer[0] && totalCost > answer[1]){
            answer[1] = totalCost;
        }
    }
}