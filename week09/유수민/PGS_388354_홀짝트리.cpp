#include <bits/stdc++.h>
using namespace std;

const int MAX = 4e5 + 4e2;
int parent[MAX];
int degree[MAX];
int rootGroup[MAX];
int nonRootGroup[MAX];
        
int find(int a){ // 부모 찾기
    if(parent[a] == a) return a;
    return parent[a] = find(parent[a]);
}

void merge(int a, int b){ // 합치기
    a = find(a);
    b = find(b);
    
    if(a != b) parent[b] = a;
}

vector<int> solution(vector<int> nodes, vector<vector<int>> edges) {
    unordered_map<int,int> node2idx; // node 번호를 0~ 적용하기 위해 변환
    
    for(int i=0; i<nodes.size(); i++){
        node2idx[nodes[i]] = i;
        parent[i] = i;
    }
    
    for(const auto& e : edges){
        int u = node2idx[e[0]];
        int v = node2idx[e[1]];
        degree[u]++;
        degree[v]++; // 연결 수 찾기
    }
    
    for(const auto& e : edges){
        int u = node2idx[e[0]];
        int v = node2idx[e[1]];
        merge(u, v);
    }
    
    // 각 노드에 대해 그룹 분류
    for(int i=0; i<nodes.size(); i++){
        int representative = find(i); // i에 해당하는 노드가 속한 연결 요소의 대표(루트) 노드를 찾는다.
        
        if((nodes[i] % 2) == (degree[i] % 2)) rootGroup[representative]++;
        else nonRootGroup[representative]++;
    }
    
    int ans1 = 0, ans2 = 0;
    for(int i=0; i<nodes.size(); i++){
        if(find(i) != i) continue; // find(i) == i인 노드가 대표 노드다.
        
        if(rootGroup[i] == 1) ans1++;
        if(nonRootGroup[i] == 1) ans2++;
    }
    
    vector<int> answer = {ans1, ans2};
    return answer;
}