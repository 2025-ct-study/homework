#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second
#define pii pair<int,int>

int n;
int m;
int board[51][51];
int board2[51][51];
int blanks;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};
int answer = INT_MAX;

vector<pii> virus;

void calc(vector<pii> cases){
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            if(board[i][j] == 1) board2[i][j] = 1;
            else board2[i][j] = INT_MAX;
        }
    }

    queue<pair<pii, int>> q; // 위치, 위치, 시간
    for(auto c : cases){
        q.push({{c.X, c.Y}, 0});
        board2[c.X][c.Y] = 0;
    }

    // 바이러스 퍼짐
    int cnt = 0;
    int time = 0;
    while(!q.empty()){
        auto cur = q.front();
        q.pop();
        cnt++;
        time = max(time, cur.Y);

        for(int t=0; t<4; t++){
            int nx = cur.X.X + dx[t];
            int ny = cur.X.Y + dy[t];
            if(nx < 1 || nx > n || ny < 1 || ny > n) continue;
            if(board2[nx][ny] != INT_MAX) continue; // 방문함

            board2[nx][ny] = cur.Y+1;
            q.push({{nx, ny}, cur.Y+1});
        }
    }

    if(cnt == blanks) answer = min(answer, time);
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> m;
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            cin >> board[i][j];
            if(board[i][j] == 2) virus.push_back({i, j});
            if(board[i][j] != 1) blanks++;
        }
    }

    // 바이러스를 놓을 위치 구하기
    int tot = virus.size();
    vector<int> brute(tot-m, 0);
    for(int i=0; i<m; i++) brute.push_back(1);

    do{
        vector<pii> cases;
        for(int i=0; i<tot; i++){
            if(brute[i] == 1) cases.push_back(virus[i]);
        }
        // 해당 위치에서 바이러스 퍼지는 시간 계산
        calc(cases);
    }while(next_permutation(brute.begin(), brute.end()));

    cout << (answer == INT_MAX ? -1 : answer);

    return 0;
}