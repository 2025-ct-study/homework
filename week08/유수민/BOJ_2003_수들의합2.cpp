#include <bits/stdc++.h>
using namespace std;

int seq[10001];
int n, m;
int answer;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> m;
    for(int i=1; i<=n; i++){
        cin >> seq[i];
    }

    int left = 1;
    int right = 1;
    int sum = seq[left];
    while(right <= n && left <= right){

        if(sum == m){
            // cout << left << " " << right << "\n";
            answer++;
            
            if(left == right){
                left++;
                right++;
                sum = seq[left];
            }
            else{
                sum -= seq[left];
                left++;
            }
            
        }
        else if(sum < m){
            sum += seq[++right];
        }
        else if(sum > m){
            if(left == right){
                left++;
                right++;
                sum = seq[left];
            }
            else{
                sum -= seq[left];
                left++;
            }
        }
    }

    cout << answer;

    return 0;
}