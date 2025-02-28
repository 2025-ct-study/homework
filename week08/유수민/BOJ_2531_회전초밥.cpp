#include <bits/stdc++.h>
using namespace std;

int n, d, k, couponNum;
int sushi[30001];
int isAte[3001];

int answer = 1;

int main(){

    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> d >> k >> couponNum;

    for(int i=1; i<=n; i++){
        cin >> sushi[i];
    }

    int count = 1; // 현재 먹은 초밥의 가짓수
    isAte[couponNum] = 1;

    for(int i=1; i<=k; i++){
        if(isAte[sushi[i]] == 0){
            count++;
        }
        isAte[sushi[i]]++;
    }
    answer = max(count, answer);

    int left = 1;
    int right = k;
    while(1){
        
        isAte[sushi[left]]--;
        if(isAte[sushi[left]] == 0) count--;

        left++;
        right++;
        if(right > n){
            right = 1;
        }
        else if(right == k) break;


        if(isAte[sushi[right]] == 0){
            count++;
        }
        isAte[sushi[right]]++;

        answer = max(count, answer);

    }

    cout << answer;

}