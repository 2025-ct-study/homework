#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second
#define pii pair<int,int>

// 0 빈칸
// 1 벽
// 2 비활성 바이러스
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

int board[51][51];
int board2[51][51];
int n, m;
int blanks;
vector<pii> virus;
int answer = INT_MAX;

void calc(vector<pii> cases){
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            if(board[i][j] == 1) board2[i][j] = -2; // 벽 
            else if(board[i][j] == 0) board2[i][j] = INT_MAX; // 빈칸
            else board2[i][j] = -1; // 바이러스
        }
    }

    queue<pair<pii, int>> q;
    for(auto c : cases){
        board2[c.X][c.Y] = 0;
        q.push({{c.X, c.Y}, 0});
    }
    int cnt = 0;
    int time = 0;
    while(!q.empty()){
        auto cur = q.front();
        q.pop();

        if(board2[cur.X.X][cur.X.Y] > 0) { // 빈칸에 퍼트렸다는 뜻
            cnt++; 
            time = max(time, cur.Y);
        }

        for(int t=0; t<4; t++){

            int nx = cur.X.X + dx[t];
            int ny = cur.X.Y + dy[t];
            if(nx < 1 || nx > n || ny < 1 || ny > n) continue;
            
            if(board2[nx][ny] == 0 || board2[nx][ny] == -2) continue; // 출발점, 벽

            if(board2[nx][ny] == -1){
                q.push({{nx, ny}, cur.Y+1});
                board2[nx][ny] = -2;
            }
            else if(board2[nx][ny] == INT_MAX){
                q.push({{nx, ny}, cur.Y+1});
                board2[nx][ny] = cur.Y+1;
            }
        }
        
    }

    if(blanks == cnt) answer = min(answer, time);

}

int main(){

    ios::sync_with_stdio(0);
    cout.tie(0);
    cin.tie(0);

    cin >> n >> m;

    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            cin >> board[i][j];
            if(board[i][j] == 0) blanks++;
            if(board[i][j] == 2) virus.push_back({i, j});
        }
    }

    vector<int> brute(virus.size()-m, 0);
    for(int i=0; i<m; i++) brute.push_back(1);

    do{
        vector<pii> cases;
        for(int i=0; i<brute.size(); i++){
            if(brute[i] == 1) cases.push_back(virus[i]);
        }
        calc(cases);
    }while(next_permutation(brute.begin(), brute.end()));

    cout << (answer == INT_MAX ? -1 : answer);

    return 0;

}