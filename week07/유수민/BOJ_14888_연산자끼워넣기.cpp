#include <bits/stdc++.h>
using namespace std;

int n;
int arr[101];

const int MAX = 1000000000;
int mn = MAX;
int mx = -1 * MAX;

int c1, c2, c3, c4; // + - * /

void calc(int a, int b, int c, int d, int res, int idx){
    
    if(idx == n+1){
        mn = min(mn, res);
        mx = max(mx, res);
        return;
    }

    if(a > 0){
        calc(a-1, b, c, d, res+arr[idx], idx+1);
    }
    if(b > 0){
        calc(a, b-1, c, d, res-arr[idx], idx+1);
    }
    if(c > 0){
        calc(a, b, c-1, d, res*arr[idx], idx+1);
    }
    if(d > 0){
        calc(a, b, c, d-1, res/arr[idx], idx+1);
    }

}

int main(){
 
    cin >> n;
    for(int i=1; i<=n; i++) cin >> arr[i];
    cin >> c1 >> c2 >> c3 >> c4;
    calc(c1, c2, c3, c4, arr[1], 2);

    cout << mx << "\n" << mn;
}