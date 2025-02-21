#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second
#define pii pair<int,int>


int n, w, l;
int truck[1001];


int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> w >> l;
    for(int i=1; i<=n; i++) cin >> truck[i];

    queue<pii> q;

    q.push({truck[1], 1}); // {트럭무게, 다리입장시간}
    int curTime = 0;
    int curWeight = truck[1];
    int nxtTruck = 2;

    while(nxtTruck < n+1){
        curTime++; // 시간 증가
        
        // 다리 끝에 도달한 경우
        auto cur = q.front();
        if(curTime - cur.Y == w) {
            q.pop(); 
            curWeight -= cur.X;
        }

        // 다리에 트럭을 더 올릴 수 있음
        if(curWeight + truck[nxtTruck] <= l && q.size()+1 <= w && curTime != 1){
            q.push({truck[nxtTruck], curTime});
            curWeight += truck[nxtTruck];
            nxtTruck++;
        }
        // cout << "curTime : " << curTime << "\n";
        // cout << "qSize : " << q.size() << "\n";
    }

    while(!q.empty()){
        auto cur = q.front();
        q.pop();
        if(curTime - cur.Y < w) curTime += (w - (curTime - cur.Y));
    }

    cout << curTime;

}