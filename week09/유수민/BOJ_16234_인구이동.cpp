#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second
#define pii pair<int,int>

int n, l, r;
int board[51][51];
bool isOpen[51][51][4];
bool isVis[51][51];

int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0}; 

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> l >> r;

    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            cin >> board[i][j];
        }
    }

    int answer = 0;
    while(1){
        for(int i=1; i<=n; i++) {
            fill(isVis[i], isVis[i]+n+1, false);
            for(int j=1; j<=n; j++) fill(isOpen[i][j], isOpen[i][j]+4, false);
        }


        // 문 열기
        int count = 0;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                int people = board[i][j];

                for(int t=0; t<4; t++){
                    int nx = i + dx[t];
                    int ny = j + dy[t];
                    if(nx < 1 || nx > n || ny < 1 || ny > n) continue;
                    int people2 = board[nx][ny];
                    if(abs(people-people2) >= l && abs(people-people2) <= r) {
                        isOpen[i][j][t] = true;
                        count++;
                    }
                }
            }
        }

        // 더 이상 인구 이동 없음
        if(count == 0) break;
        answer++;


        // 인구 이동 시키기
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                
                if(isVis[i][j]) continue;

                vector<pii> temp;
                isVis[i][j] = true;
                int sum = 0;

                queue<pii> q;
                q.push({i, j});
                while(!q.empty()){
                    pii cur = q.front();
                    q.pop();
                    sum += board[cur.X][cur.Y];
                    temp.push_back({cur.X, cur.Y});

                    for(int t=0; t<4; t++){
                        int nx = cur.X + dx[t];
                        int ny = cur.Y + dy[t];
                        if(nx < 1 || nx > n || ny < 1 || ny > n) continue;
                        if(isVis[nx][ny]) continue;
                        if(isOpen[cur.X][cur.Y][t]){
                            q.push({nx, ny});
                            isVis[nx][ny] = true;
                        }
                    }
                }
                
                if(temp.size() == 1) continue;

                int p = sum / temp.size();
                for(const pii& e : temp){
                    board[e.X][e.Y] = p;
                }
            }
        }
        
    }

    cout << answer;
}