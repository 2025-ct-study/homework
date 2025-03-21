#include <bits/stdc++.h>
using namespace std;

vector<map<string, int>> trie; // 각 노드를 map으로 표현

void insert(vector<string> str){

    int cur = 0;
    for(const string& food : str){

        if(trie[cur].find(food) == trie[cur].end()){
            trie[cur][food] = trie.size();
            trie.push_back(map<string,int>());
        }
        cur = trie[cur][food];
    }
}

void print(int cur, int depth){
    for (auto &p : trie[cur]) {
        for (int i = 0; i < depth; i++) {
            cout << "--";
        }
        cout << p.first << "\n";
        print(p.second, depth + 1);
    }
    
}

int main(){

    int n;
    cin >> n;

    trie.push_back(map<string, int>());

    while(n--){

        int k;
        cin >> k;
        vector<string> foods;
        while(k--){
            string food;
            cin >> food;
            foods.push_back(food);
        }
        insert(foods);
    }

    print(0, 0);
    
}