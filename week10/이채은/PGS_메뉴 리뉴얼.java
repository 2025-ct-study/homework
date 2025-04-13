import java.util.*;

class Solution {
    public Map<String, Integer> hm;
    public int max;

    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> answer = new ArrayList<>();

        // 각 order 정렬
        for(int i = 0; i < orders.length; i++){
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = new String(arr);
        }

        // course[i]개의 단품요리 조합
        for(int i = 0; i < course.length; i++){
            // 모든 order에 대해 조합 생성, 조합과 갯수 저장
            hm = new HashMap<>();
            max = 0;
            for(int j = 0; j < orders.length; j++){
                comb(orders[j], course[i], 0, "");
            }

            // 생성된 조합 중, 갯수가 2개 이상이고 max인 조합 = 코스요리
            for(Map.Entry<String,Integer> entry : hm.entrySet()){
                int cnt = entry.getValue();
                if(cnt >= 2 && cnt == max) answer.add(entry.getKey());
            }
        }

        return answer.stream().sorted().toArray(String[]::new);
    }

    public void comb(String order, int course, int idx, String str){
        if(str.length() == course){
            hm.put(str, hm.getOrDefault(str,0)+1);
            max = Math.max(max,hm.get(str));
            return;
        }

        for(int i = idx; i < order.length(); i++){
            comb(order, course, i+1, str+order.charAt(i));
        }
    }
}