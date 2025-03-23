import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        Set<String> set = new HashSet<>(Arrays.asList(gems));
        Map<String, Integer> gemsMap = new HashMap<>();

        // 투포인터
        int start = 0, end = 0;
        int typeCnt = set.size();
        int min = Integer.MAX_VALUE;
        while(end < gems.length){
            gemsMap.put(gems[end], gemsMap.getOrDefault(gems[end], 0)+1);

            if(gemsMap.size() == typeCnt){
                while(gemsMap.get(gems[start]) > 1){
                    gemsMap.put(gems[start], gemsMap.get(gems[start])-1);
                    start++;
                }

                if(min > end - start){
                    min = end - start;
                    answer[0] = start+1;
                    answer[1] = end+1;
                }
            }
            end++;
        }
        return answer;
    }
}