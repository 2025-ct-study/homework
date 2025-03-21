#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second 
#define pii pair<int,int>

int dx[4] = {0, 1, 0, -1}; // 1, 3 : 좌우
int dy[4] = {1, 0, -1, 0};
char board[11][11];
int N, M;

struct State{
    int rx, ry, bx, by, moves;
};

pii moveBeads(int x, int y, int dir, int& moveCnt){

    while(board[x + dx[dir]][y + dy[dir]] != '#' && board[x][y] != 'O'){
        x += dx[dir];
        y += dy[dir];
        moveCnt++;
        if(board[x][y] == 'O') break;
    }

    return {x, y};
}

int bfs(int sx, int sy, int bx, int by){
    
    bool visited[10][10][10][10] = {false}; // 빨간구슬, 파란구슬 위치 조합

    queue<State> q;
    q.push({sx, sy, bx, by, 0});
    visited[sx][sy][bx][by] = true;

    while(!q.empty()){
        const State cur = q.front();
        q.pop();

        if(cur.moves >= 10) continue;

        for(int dir=0; dir<4; dir++){
            int nrx, nry, nbx, nby;
            int r_moveCnt = 0, b_moveCnt = 0;
            tie(nrx, nry) = moveBeads(cur.rx, cur.ry, dir, r_moveCnt);
            tie(nbx, nby) = moveBeads(cur.bx, cur.by, dir, b_moveCnt);

            if(board[nbx][nby] == 'O') continue; // 실패
            if(board[nrx][nry] == 'O') return cur.moves + 1;

            if(nrx == nbx && nry == nby){
                if(r_moveCnt > b_moveCnt){
                    nrx -= dx[dir];
                    nry -= dy[dir];
                }
                else{
                    nbx -= dx[dir];
                    nby -= dy[dir];
                }
            }   

            if(!visited[nrx][nry][nbx][nby]){
                visited[nrx][nry][nbx][nby] = true;
                q.push({nrx, nry, nbx, nby, cur.moves+1});
            }
        }
    }

    return -1;
}

int main(){

    cin >> N >> M;

    int rx, ry, bx, by;

    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin >> board[i][j];
            
            if(board[i][j] == 'R') {
                rx = i, ry = j;
                board[i][j] = '.';
            }
            else if(board[i][j] == 'B') {
                bx = i, by = j;
                board[i][j] = '.';
            }
        }
    }

    cout << bfs(rx, ry, bx, by);
    return 0;
}