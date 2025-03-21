#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second
#define pii pair<int,char>

int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};
int curDir;
int curHead;
int curTail;

int n;
int board[101][101];
int apples;
int cnt;
vector<pii> rotates;

int main(){

    cin >> n;
    cin >> apples;

    while(apples--){
        int x, y;
        cin >> x >> y;
        board[x][y] = 1; // 사과
    }

    cin >> cnt;

    queue<pii> q; // {시간, 방향}
    while(cnt--){
        int x;
        char c;
        cin >> x >> c;
        q.push({x, c});
    }

    board[1][1] = 2; // 초기 위치
    curHead  = 2; // 뱀 현재 머리 번호
    curTail = 2; // 뱀 현재 꼬리 번호
    
    curDir = 0; // 초기 방향
    int hx = 1, hy = 1; // 현재 머리 위치
    int tx = 1, ty = 1; // 현재 꼬리 위치

    int curTime = 0;
    while(1){

        // cout << "\n" << "curTime : " << curTime << "\n";
        // for(int i=1; i<=n; i++){
        //     for(int j=1; j<=n; j++){
        //         cout << board[i][j] << " ";
        //     }
        //     cout << "\n";
        // }

        curTime++;

        int nx = hx + dx[curDir];
        int ny = hy + dy[curDir];
        
        if(nx < 1 || nx > n || ny < 1 || ny > n) {
            // cout << "Wall \n";
            break; // 벽
        }

        if(board[nx][ny] >= 2) {
            // cout << "Body \n";
            break; // 자기 몸에 
        }

        
        bool check = false;
        if(board[nx][ny] != 1){
            board[tx][ty] = 0; // 꼬리 지우기
            curTail++;
            check = true;
        }

        board[nx][ny] = ++curHead;
        hx = nx, hy = ny;

        if(check){
            for(int k=0; k<4; k++){
                int x = tx + dx[k];
                int y = ty + dy[k];
    
                if(x < 1 || x > n || y < 1 || y > n) continue;
                if(board[x][y] == curTail){
                    tx = x;
                    ty = y;
                    break;
                }
            }
        }

        if(q.empty()) continue;
        const pii& cur = q.front();
        if(cur.X == curTime){ // 회전
            if(cur.Y == 'D') curDir = (curDir+1)%4;
            else curDir = (curDir+3)%4;
            q.pop();
        }
    }

    cout << curTime;


}