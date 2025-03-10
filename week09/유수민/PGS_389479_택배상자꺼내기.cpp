#include <bits/stdc++.h>
using namespace std;

int stacks[101][11];
int height;

int solution(int n, int w, int num) {
    
    height = n / w;
    if(n % w != 0) height += 1;
    
    int dir = 1; // 1: 오른쪽으로 -1: 왼쪽으로
    int i = height;
    int box = 1;
    
    while(box <= n && i > 0){
        if(dir == 1){
            for(int j=1; j<=w; j++){
                stacks[i][j] = box++;
                if(box > n) break;
            }
        }
        else{
            for(int j=w; j>=1; j--){
                stacks[i][j] = box++;
                if(box > n) break;
            }
        }
        dir *= -1;
        i--;
    }
    
    // for(int i=1; i<=height; i++){
    //     for(int j=1; j<=w; j++){
    //         cout << stacks[i][j] << " ";
    //     }
    //     cout << "\n";
    // }

    int gx, gy;
    for(int x=1; x<=height; x++){
        for(int y=1; y<=w; y++){
            if(stacks[x][y] == num){
                gx = x;
                gy = y;
                break;
            }
        }
    }
    
    cout << gx <<" " <<  gy;
    
    int answer = 0;
    for(int t=1; t<=gx; t++){
        if(stacks[t][gy] != 0) answer++;
    }
    

    return answer;
}