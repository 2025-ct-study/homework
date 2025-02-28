#include <bits/stdc++.h>
using namespace std;

#define pii pair<int,int>
#define X first
#define Y second

int n, k;
int lineSum = 0;
int lineLen[1000001];
vector<pii> lines;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> k;

    for(int i=0; i<n; i++){
        int a, b;
        cin >> a >> b;
        lines.push_back({a, b});
        lineSum += (b-a);
        for(; a+1<=b; a++) lineLen[a]++;
    }

    if(lineSum < k){
        cout << 0 << " " << 0;
        return 0;
    }

    sort(lines.begin(), lines.end());
    int left = 0;
    int right = 0;
    int sum = 0;


    while(left <= right && right<=1000000){

        if(sum == k){
            cout << left  << " " << right;
            break;
        }
        else if(sum < k){
            sum += lineLen[right++];
        }
        else if(sum > k){
            sum -= lineLen[left++];
        }
    }


}
    