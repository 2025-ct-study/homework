#include <bits/stdc++.h>
using namespace std;

#define ll long long

int n;
string cmd;
ll res = INT_MIN;

void calc(vector<string> problem){
    ll tot = stoi(problem[0]);
    string command;
    for(int i=1; i<problem.size(); i++){
        if(problem[i] == "+" || problem[i] == "-" || problem[i] == "*"){
            command = problem[i];
        }
        else{
            ll number = stoi(problem[i]);
            if(command == "+"){
                tot += number;
            }
            else if(command == "-"){
                tot -= number;
            }
            else if(command == "*"){
                tot *= number;
            }
        }
    }

    res = max(res, tot);
}

void dfs(int idx, vector<string> problem){

    if(idx >= n){
        // for(auto e : problem) cout << e << " ";
        // cout << "\n"; 
        calc(problem);
        return;
    }

    // 열기
    if(idx+2 < n){
        vector<string> temp1 = problem;
        int r1 = cmd[idx]-'0';
        int r2 = cmd[idx+2]-'0';
        int c = cmd[idx+1];
        switch(c){
            case '+':
                r1 += r2;
                break;
            case '-':
                r1 -= r2;
                break;
            case '*':
                r1 *= r2;
                break;
        }
        temp1.push_back(to_string(r1));
        if(idx+4 < n){
            string str = "";
            str += cmd[idx+3];
            temp1.push_back(str);
        }
        dfs(idx+4, temp1);
    }


    // 열지 않기
    vector<string> temp2 = problem;
    for(int i=idx; i<=idx+1; i++){
        if(i >= n) break;
        string str = "";
        str += cmd[i];
        temp2.push_back(str);
    }
    dfs(idx+2, temp2);
    
}

int main(){

    cin >> n;
    cin >> cmd;

    vector<string> temp;
    if(n != 1) dfs(0, temp);
    else res = stoi(cmd);

    cout << res;
}