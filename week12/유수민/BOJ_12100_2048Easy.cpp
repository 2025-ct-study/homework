#include <bits/stdc++.h>
using namespace std;

int n;
int board[21][21];
int board2[21][21];

void rotate(){ // 시계 방향으로 한 바퀴 회전 시키기
    int tmp[21][21];
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            tmp[i][j] = board[i][j];
        }
    }
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            board[i][j] = tmp[n+1-j][i];
        }
    }
}

void play(int dir){ // 왼쪽으로 밀기
    while(dir--) rotate();

    for(int i=1; i<=n; i++){
        int idx = 0;
        int tilted[21] = {};
        for(int j=1; j<=n; j++){
            if(board[i][j] == 0) continue;
            // 숫자가 있는 것들
            if(tilted[idx] == 0) tilted[idx] = board[i][j];
            else if(tilted[idx] == board[i][j]) tilted[idx++] *= 2;
            else tilted[++idx] = board[i][j];
        }

        for(int j=1; j<=n; j++){
            board[i][j] = tilted[j-1];
        }
    }

}


int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n;
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            cin >> board2[i][j];
        }
    }

    int mx = 0;
    for(int i=0; i<1024; i++){
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                board[i][j] = board2[i][j];
            }
        }
        int brute = i;
        for(int i=0; i<5; i++){
            int dir = brute % 4;
            brute /= 4;
            play(dir);
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                mx = max(mx, board[i][j]);
            }
        }
    }
    cout << mx;
}