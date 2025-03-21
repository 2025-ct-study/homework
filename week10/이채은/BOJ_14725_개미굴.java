import java.io.*;
import java.util.*;

public class Main {
    public static class Node {
        public Map<String, Node> child;
        public boolean terminal;

        public Node(){
            this.child = new TreeMap<>(); // 정렬을 위해 트리맵 사용
            this.terminal = false;
        }
    }

    public static class Trie{
        Node root;

        public Trie(){
            this.root = new Node();
        }

        public void insert(String[] treats){
            Node node = this.root;
            for(int i = 0; i < treats.length; i++){
                String treat = treats[i];
                node.child.putIfAbsent(treat,new Node());
                node = node.child.get(treat);
            }
            node.terminal = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Trie trie = new Trie();
        int N = Integer.parseInt(br.readLine());
        for(int n = 0; n < N; n++){
            // 각 로봇개미의 먹이정보
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            String[] threats = new String[K];
            for(int k = 0; k < K; k++){
                threats[k] = st.nextToken();
            }
            trie.insert(threats);
        }

        // trie를 탐색하며 먹이구조 출력
        dfs(0, trie.root, sb);
        System.out.println(sb);
    }

    public static void dfs(int cnt, Node node, StringBuilder sb){
        if(node.child.isEmpty()) return;

        for(Map.Entry<String,Node> entry : node.child.entrySet()){
            sb.append("--".repeat(cnt)).append(entry.getKey()).append('\n');
            dfs(cnt+1, entry.getValue(), sb);
        }
    }
}