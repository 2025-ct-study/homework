#include <bits/stdc++.h>
using namespace std;

typedef pair<int, int> pii;
#define X first
#define Y second

vector<pii> cctv;
int n, m;
int board1[10][10], board2[10][10];
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

void upd(int x, int y, int dir) 
{
    dir%=4;
    while(1){
        x += dx[dir];
        y += dy[dir];
        if(x < 0 || x >= n || y < 0 || y >= m) return; // 범위초과
        else if(board2[x][y] == 6) return; // 벽
        else if(board2[x][y] !=0) continue; // cctv 위치 이므로 넘어간다.
        board2[x][y] = 7; // 빈칸을 7로 덮는다.
    }
}

int main(){
    cin >> n >> m; // 세로 가로
    int nm = 0;
    for(int i=0; i<n; i++)
    {
        for(int j=0; j<m; j++)
        {
            cin >> board1[i][j];
            if(board1[i][j] != 0 && board1[i][j] != 6) cctv.push_back({i, j});
            if(board1[i][j] == 0) nm++;
        }
    }
    
    for(int tmp=0; tmp<(1<<(2*cctv.size())); tmp++)
    {
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
                board2[i][j] = board1[i][j]; // 매번 board2 새로고침
                
        int brute = tmp;
        for(int i=0; i<cctv.size(); i++)
        {
            int dir = brute % 4; // 방향을 결정
            brute /= 4; // 다음 방향을 결정

            int x = cctv[i].X;
            int y = cctv[i].Y;
            if(board1[x][y] == 1){
                upd(x,y,dir);
            }
            else if(board1[x][y] == 2){
                upd(x,y,dir);
                upd(x,y,dir+2);
            }
            else if(board1[x][y] == 3){
                upd(x,y,dir);
                upd(x,y,dir+1);
            }
            else if(board1[x][y] == 4){
                upd(x,y,dir);
                upd(x,y,dir+1);
                upd(x,y,dir+2);
            }
            else{ // board1[x][y] == 5
                upd(x,y,dir);
                upd(x,y,dir+1);
                upd(x,y,dir+2);
                upd(x,y,dir+3);
            }
        }
        int ans = 0;
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
                ans += (board2[i][j]==0); // 사각지대 개수 카운트
        
        nm = min(nm, ans);   
    }
    cout << nm;
}