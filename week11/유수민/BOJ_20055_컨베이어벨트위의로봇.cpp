#include <bits/stdc++.h>
using namespace std;

int N, K;
int arr[201];
bool isExist[201];

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> K;
    for(int i = 0; i < 2 * N; i++){
        cin >> arr[i];
    }
    
    int step = 1;
    while(true) {

        int last = arr[2 * N - 1];
        for (int i = 2 * N - 1; i > 0; i--){
            arr[i] = arr[i - 1];
        }
        arr[0] = last;
        
        for (int i = N - 1; i > 0; i--){
            isExist[i] = isExist[i - 1];
        }
        isExist[0] = false;

        if (isExist[N - 1]) isExist[N - 1] = false;
        

        for (int i = N - 2; i >= 0; i--){
            if(isExist[i] && !isExist[i+1] && arr[i+1] > 0){
                isExist[i] = false;
                if(i + 1 != N - 1){
                    isExist[i+1] = true;
                }
                arr[i+1]--;
            }
        }
        if(isExist[N - 1]) isExist[N - 1] = false;
        
        if(arr[0] > 0){
            isExist[0] = true;
            arr[0]--;
        }
        
        int cnt = 0;
        for(int i = 0; i < 2 * N; i++){
            if(arr[i] == 0) cnt++;
        }
        if(cnt >= K){
            cout << step;
            break;
        }
        step++;
    }
    
    return 0;
}
