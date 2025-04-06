#include <bits/stdc++.h>
using namespace std;

#define ll long long

int coin[101];
int dp[10001];

int main(){

    int testcase;
    cin >> testcase;

    vector<ll> answer;
    while(testcase--){
        int n, m;
        cin >> n;

        fill(coin, coin+n+1, 0);

        for(int i=0; i<n; i++){
            cin >> coin[i];
        }
        cin >> m;

        fill(dp, dp+m+1, 0);
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = coin[i]; j <= m; j++) {
                dp[j] += dp[j - coin[i]];
            }
        }

        answer.push_back(dp[m]);
    }

    for(const ll a : answer) cout << a << "\n";

}