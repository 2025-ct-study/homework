import java.util.*;

class Solution {
    public HashMap<Integer,ArrayList<Integer>> child;
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];

        // (노드 - 자식노드list) hashmap
        child = new HashMap<>();
        for(int i = 0; i < nodes.length; i++){
            child.put(nodes[i], new ArrayList<>());
        }

        for(int i = 0; i < edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            child.get(from).add(to);
            child.get(to).add(from);
        }

        Set<Integer> visited = new HashSet<>();
        for(int i = 0; i < nodes.length; i++){
            if(visited.contains(nodes[i])) continue;
            int[] tree = new int[2]; // tree[0]: 홀짝 노드 갯수, tree[1]: 역홀짝 노드 갯수

            // 트리 탐색 (bfs)
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(nodes[i]);
            visited.add(nodes[i]);
            while(!q.isEmpty()){
                int cur = q.poll();
                tree[getNodeType(cur)]++; //루트가 아닐 때의 노드 타입

                for(int next : child.get(cur)){
                    if(visited.contains(next)) continue;
                    q.offer(next);
                    visited.add(next);
                }
            }

            // 홀짝 노드가 하나면 역홀짝트리, 역홀짝 노드가 하나면 홀짝트리
            if(tree[0] == 1) answer[1]++;
            if(tree[1] == 1) answer[0]++;
        }

        return answer;
    }

    public int getNodeType(int node){
        if(node%2 == (child.get(node).size()-1)%2) return 0;
        else return 1;
    }
}