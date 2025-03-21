import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        Set<String> set = new HashSet(Arrays.asList(gems));
        int type = set.size();

        int start = 0, end = 0;
        int min = Integer.MAX_VALUE;
        Map<String,Integer> hm = new HashMap<>();
        while(end < gems.length){
            hm.put(gems[end], hm.getOrDefault(gems[end],0)+1);

            if(hm.size() == type){
                while(hm.get(gems[start])-1 > 0){
                    hm.put(gems[start],hm.get(gems[start])-1);
                    start++;
                }

                if(end-start < min){
                    min = end-start;
                    answer[0] = start+1;
                    answer[1] = end+1;
                }
            }
            end++;
        }

        return answer;
    }
}