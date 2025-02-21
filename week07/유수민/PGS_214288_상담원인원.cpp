#include <bits/stdc++.h>
using namespace std;
#define X first
#define Y second
#define pii pair<int,int>

int K, N;
int answer = INT_MAX;
vector<vector<int>> mento;
vector<pii> waiting[5];

void makeMento(vector<int> res, int cnt, int idx){ // tc, 남은멘토수
    
    if(cnt == 0){
        mento.push_back(res);
        return;
    }
    else if(idx >= K) return;

    for(int i=0; i<=cnt; i++){
        makeMento(res, cnt-i, idx+1);
        res[idx]++;
    }
}

void calc(vector<int> res){
    int time = 0;
    
    for(auto e : res) cout << e << " ";
    cout << "\n";
    
    for(int type=0; type<K; type++){
        int leftMento = res[type]; // 현재 비어있는 멘토
        priority_queue<int, vector<int>, greater<int>> pq; // 끝나는 시간 빠른 순
        
        for(pii& waits : waiting[type]){
            if(leftMento > 0){
                pq.push(waits.Y);
                leftMento--;
            }
            else{
                int tp = pq.top();
                pq.pop();
                
                if(waits.X < tp) {
                    time += (tp - waits.X);
                    pq.push(tp + waits.Y-waits.X);
                }
                else{
                    pq.push(waits.Y);
                }
                
            }
        }
    }
    
    answer = min(answer, time);
}

// 기다린 시간만 집계
// 멘토를 적절히 배정해야됨.

int solution(int k, int n, vector<vector<int>> reqs) {
    K = k;
    N = n;
    
    vector<int> temp;
    for(int i=0; i<k; i++){
        temp.push_back(1);
    }
    makeMento(temp, n-k, 0);
    
    
    for(auto& req : reqs){
        waiting[req[2]-1].push_back({req[0], req[0]+req[1]});
    }
    for(int i=0; i<k; i++){
        sort(waiting[i].begin(), waiting[i].end());
    }
    
    for(auto m : mento){
        calc(m);
    }
    
    return answer;
}