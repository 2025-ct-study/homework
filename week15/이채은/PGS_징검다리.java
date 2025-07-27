import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);

        int left = 1;
        int right = distance;
        while(left <= right){
            int mid = (left + right) / 2;

            if(removeRocks(distance, rocks, mid) <= n) left = mid+1;
            else right = mid-1;
        }

        return left-1;
    }

    public int removeRocks(int distance, int[] rocks, int minDist){
        int cnt = 0;
        int curRock = 0;
        for(int i = 0; i < rocks.length; i++){
            if(rocks[i] - curRock < minDist){
                cnt++;
            } else{
                curRock = rocks[i];
            }
        }

        return distance - curRock < minDist ? cnt+1 : cnt;
    }
}