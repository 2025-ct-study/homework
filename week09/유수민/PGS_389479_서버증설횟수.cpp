#include <bits/stdc++.h>
using namespace std;

int solution(vector<int> players, int m, int k) { // m명 늘어날때마다 서버 1대 추가
    
    int curTime = 0; // 현재 시간
    int curPlayer = 0; // 서버할당 안받고 남아있는 플레이어수
    int answer = 0;
    
    
    priority_queue<int, vector<int>, greater<int>> q; // {서버가 종료되는 시각}
    
    for(int i=0; i<24; i++){ // 0~1 1~2
        curTime = i;
        int player = players[i]; // 이 시각의 이용자 수
        
        if(player < m) continue;
        
        
        int cnt = player / m;
        while(!q.empty() && q.top() <= curTime) q.pop();
        cnt -= q.size();
        
        if(cnt > 0){
            
            answer += cnt;
            while(cnt--) q.push(curTime + k);
        }
    }
    
    
    
    return answer;
}