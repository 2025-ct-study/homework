#include <bits/stdc++.h>
using namespace std;

#define MX 100005

int n, s;
int seq[100001];

int main(){
    ios::sync_with_stdio(0);
    cout.tie(0);
    cin.tie(0);

    cin >> n >> s;
    for(int i=0; i<n; i++){
        cin >> seq[i];
    }

    int minLen = MX;

    int left = 0;
    int right = 0;
    int sum = seq[left];

    while(left <= right && right < n){
        if(sum >= s){
            minLen = min(minLen, right-left+1);
            sum -= seq[left++];
            
        }
        else{
            sum += seq[++right];
        }
    }

    cout << (minLen == MX ? 0 : minLen);

    return 0;
}