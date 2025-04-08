#include <bits/stdc++.h>
using namespace std;

int coin[101];
int dp[10001];

int main(){

    int n, k;
    cin >> n >> k;
    
    fill(dp, dp+k+1, INT_MAX);
    dp[0] = 0;

    for(int i=1; i<=n; i++){
        cin >> coin[i];
        for (int j = coin[i]; j <= k; j++) {
            if(dp[j - coin[i]] != INT_MAX) {
                dp[j] = min(dp[j], dp[j - coin[i]] + 1);
            }
        }
    }

    if(dp[k] == INT_MAX) cout << -1;
    else cout << dp[k];


}