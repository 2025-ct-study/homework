#include <bits/stdc++.h>
using namespace std;

#define pii pair<int,int>
#define X first
#define Y second

int seq[1000001];
int n;
int answer[1000001];


int main(){

    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n;
    stack<pii> s;
    for(int i=1; i<=n; i++){
        cin >> seq[i];

        while(!s.empty()){
            pii cur = s.top();
            if(cur.X < seq[i]){
                answer[cur.Y] = seq[i];
                s.pop();
            }
            else{
                break;
            }
        }
        s.push({seq[i], i});
    }
    while(!s.empty()){
        pii cur = s.top();
        s.pop();
        answer[cur.Y] = -1;
    }

    for(int i=1; i<=n; i++) cout << answer[i] << " ";
}