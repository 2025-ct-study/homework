#include <bits/stdc++.h>
using namespace std;

const int MX = 4000000;

int n;
bool isRemove[MX+1];

vector<int> decimals;


int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n;

    if(n == 1){
        cout << 0;
        return 0;
    }

    for(int i=2; i<=n; i++){
        if(isRemove[i] == true) continue;

        for(int j=i*2; j<=n; j+=i){
            isRemove[j] = true;
        }
    }

    for(int i=2; i<=n; i++){
        if(!isRemove[i]) decimals.push_back(i);
    }
    
    int answer = 0;
    // 만들 수 있는 길이
    int left = 0;
    int right = 0;
    int sum = decimals[left];

    while(left <= right && right < decimals.size()){
        if(sum == n) {
            answer++;
            sum -= decimals[left++];
        }

        else if(sum > n){
            sum -= decimals[left++];
        }
        else if(sum < n){
            sum += decimals[++right];
        }
    }


    // 2 3 5 7 11 13 17 19 23 29
    cout << answer;
}