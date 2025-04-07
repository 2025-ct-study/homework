#include <bits/stdc++.h>
using namespace std;

unordered_map<string, int> umap;
vector<string> cases;

void dfs(const string &str, int idx, string res, unordered_set<string> &vis) {
    
    if(res.size() >= 2) { 
        if(vis.find(res) == vis.end()){
            vis.insert(res);
            umap[res]++;   
            cases.push_back(res);
        }
    }

    for(int i = idx; i < str.size(); i++){
        dfs(str, i+1, res + str[i], vis);
    }
}

vector<string> solution(vector<string> orders, vector<int> course) {
    
    for(auto &order : orders){
        sort(order.begin(), order.end());
        unordered_set<string> vis; 
        dfs(order, 0, "", vis);
    }
 
    vector<string> answer;
    
    for (int c : course) {
        
        int maxFreq = 0;
        vector<string> candidates;
        
        for (auto &p : umap) {
            
            if(p.first.size() == c && p.second >= 2) {
                if(p.second > maxFreq) {
                    maxFreq = p.second;
                    candidates.clear();
                    candidates.push_back(p.first);
                } 
                else if(p.second == maxFreq) {
                    candidates.push_back(p.first);
                }
            }
        }
        answer.insert(answer.end(), candidates.begin(), candidates.end());
    }
    
    sort(answer.begin(), answer.end());
    return answer;
    
    
    return answer;
}