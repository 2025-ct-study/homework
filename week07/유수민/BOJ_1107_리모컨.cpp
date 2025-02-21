#include <bits/stdc++.h>
using namespace std;

string n;
int m;
bool isBroken[10];
vector<int> nonBroken;
int cur = 100;
int answer = INT_MAX;

void dfs(int len, string res){
    if(res != "") answer = min(answer, abs(stoi(res)-stoi(n))+len);

    if(len == n.length()+1){
        return;
    }

    for(auto e : nonBroken){
        string temp = res;
        temp += to_string(e);
        dfs(len+1, temp);
    }
}

int main(){

    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n;
    cin >> m;
    for(int i=0; i<m; i++){
        int t;
        cin >> t;
        isBroken[t] = true;
    }
    for(int i=0; i<10; i++){
        if(!isBroken[i]) nonBroken.push_back(i);
    }
    answer = min(abs(stoi(n)-cur), answer);

    if(nonBroken.size() != 0){
        dfs(0, "");
    }
    
    
    cout << answer;
}