#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second
#define pii pair<int,int>

int cave[125][125];
int dist[125][125];
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};


int main(){
    
    vector<int> answer;

    while(1){
        int n;
        cin >> n;
        if(n == 0) break;

        for(int i=0; i<n; i++) fill(dist[i], dist[i]+n, INT_MAX);

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                cin >> cave[i][j];
            }
        }

        priority_queue<pair<int, pii>, vector<pair<int, pii>>, greater<pair<int, pii>>> pq;

        dist[0][0] = cave[0][0];
        pq.push({dist[0][0], {0, 0}});

        while(!pq.empty()){
            const auto cur = pq.top();
            pq.pop();

            if(cur.X > dist[cur.Y.X][cur.Y.Y]) continue;

            for(int d=0; d<4; d++){
                int nx = cur.Y.X + dx[d];
                int ny = cur.Y.Y + dy[d];
                

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                if (dist[nx][ny] > dist[cur.Y.X][cur.Y.Y] + cave[nx][ny]) {
                    dist[nx][ny] = dist[cur.Y.X][cur.Y.Y] + cave[nx][ny];
                    pq.push({dist[nx][ny], {nx, ny}});
                }
            }
        }   
        
        answer.push_back(dist[n-1][n-1]);
    }

    

    for(int a=0; a<answer.size(); a++) cout << "Problem " << a+1 << ": " << answer[a] << "\n";
}