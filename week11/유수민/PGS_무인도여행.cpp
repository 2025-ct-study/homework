#include <bits/stdc++.h>
using namespace std;

int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

#define pii pair<int,int>
#define X first
#define Y second

int n, m;
bool vis[101][101];

vector<int> solution(vector<string> maps) {
    
    n = maps.size();
    m = maps[0].size();
    vector<pii> starter;
    
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            if(maps[i][j] != 'X') starter.push_back({i, j});
        }
    }
    
    vector<int> answer;
    
    for(const pii& st : starter){
        if(vis[st.X][st.Y]) continue;
        
        int sum = 0;
        queue<pii> q;
        q.push({st.X, st.Y});
        vis[st.X][st.Y] = true;
        while(!q.empty()){
            const pii& cur = q.front();
            q.pop();
            sum += (maps[cur.X][cur.Y] - '0');
            
            for(int t=0; t<4; t++){
                int nx = cur.X + dx[t];
                int ny = cur.Y + dy[t];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                else if(vis[nx][ny]) continue;
                else if(maps[nx][ny] == 'X') continue;
                q.push({nx, ny});
                vis[nx][ny] = true;
            }
        }
        answer.push_back(sum);
    }
    
    sort(answer.begin(), answer.end());
    if(answer.size() == 0) answer.push_back(-1);
    return answer;
}