import java.util.*;

class Solution {
    public static class EdgeInfo{
        int in, out;
        public EdgeInfo(int in, int out){
            this.in = in;
            this.out = out;
        }
    }

    public int[] solution(int[][] edges) {
        // 생성한 정점 : in 0 & out 2 이상
        // 막대 모양 : out 0
        // 8자 모양 : in 2 이상 out 2
        // 도넛 모양 : 생성한 쟁점 out 수 - 막대모양 수 - 8자 모양 수

        Map<Integer,EdgeInfo> nodes = new HashMap<>();
        for(int i = 0; i < edges.length; i++){
            EdgeInfo inInfo = nodes.getOrDefault(edges[i][1], new EdgeInfo(0,0));
            inInfo.in++;
            nodes.put(edges[i][1], inInfo);

            EdgeInfo outInfo = nodes.getOrDefault(edges[i][0], new EdgeInfo(0,0));
            outInfo.out++;
            nodes.put(edges[i][0], outInfo);
        }

        int[] answer = new int[4];
        for(Map.Entry<Integer,EdgeInfo> node : nodes.entrySet()){
            EdgeInfo edgeInfo = node.getValue();
            int in = edgeInfo.in;
            int out = edgeInfo.out;

            if(in == 0 && out >= 2) answer[0] = node.getKey();
            else if(out == 0) answer[2]++;
            else if(in >= 2 && out == 2) answer[3]++;
        }
        answer[1] = nodes.get(answer[0]).out - answer[2] - answer[3];

        return answer;
    }
}