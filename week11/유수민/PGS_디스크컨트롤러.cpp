#include <bits/stdc++.h>
using namespace std;

#define pii pair<int,int>
#define X first
#define Y second

int solution(vector<vector<int>> jobs) {
    sort(jobs.begin(), jobs.end()); // 요청 시간에 따라 정렬

    priority_queue<pii, vector<pii>, greater<pii>> pq; // 최소 힙
    int idx = 0;
    int t = 0; //현재 시각 
    int answer = 0, n = jobs.size();

    while(idx < n || !pq.empty()) {
        if(pq.empty() && t < jobs[idx][0]) t = jobs[idx][0];
        
        while(idx < n && jobs[idx][0] <= t) {
            pq.push({jobs[idx][1], jobs[idx][0]});
            idx++;
        }
        
        if(!pq.empty()) {
            pii cur = pq.top();
            pq.pop();
            t += cur.X;
            answer += t - cur.Y;
        }
    }
    
    return answer / n;
}
