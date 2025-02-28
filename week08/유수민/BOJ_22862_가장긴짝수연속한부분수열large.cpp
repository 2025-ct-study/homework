#include <bits/stdc++.h>
using namespace std;

int n, k;
int seq[1000005];
int answer = 0;

int main(){

    cin >> n >> k;
    for(int i=1; i<=n; i++){
        cin >> seq[i];
    }

    int left = 1;
    int right = 2;
    int curLen = (seq[1] % 2 == 0 ? 1 : 0); // 현재 길이
    int curRemove = (seq[1] % 2 == 0 ? 0 : 1); // 현재 삭제 횟수

    answer = max(answer, curLen);

    while(left <= right && right <= n){

        // 짝수
        if(seq[right] % 2 == 0){
            curLen++;
            right++;
        }

        // 홀수
        else{
            while(curRemove+1 > k){
                if(seq[left] % 2 == 0){
                    curLen--; 
                }
                else{
                    curRemove--;
                }
                left++;
            } 
            right++;
            curRemove++;
        }
        
        

        answer = max(answer, curLen);
    }

    cout << answer;

    return 0;
}