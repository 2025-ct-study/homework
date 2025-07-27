class Solution {
    public int[] solution(int brown, int yellow) {
        int total = brown + yellow;

        int w = 0, h = 0;
        for(h = 3; h < total/2; h++) {
            if(total % h == 0){
                w = total / h;

                int calBrown = (w-1)*2 + (h-1)*2;
                int calYellow = (w-2) * (h-2);
                if(calBrown == brown && calYellow == yellow) break;
            }
        }

        int[] answer = {w,h};
        return answer;
    }
}