#include <bits/stdc++.h>
using namespace std;

#define ll long long

bool isUsed[11];
char eq[11];
int k;

ll mx = 0;
string sMX = "";
ll mn = 9876543210;
string sMN = "";

ll makeNum(vector<int> res){

    ll ans = 0;
    ll ten = 1;
    for(int i = res.size()-1; i>=0; i--){
        ans += (ten * res[i]);
        ten *= 10;
    }
    return ans;
}

void func(vector<int> res){
    if(res.size() == k+1){
        ll result = makeNum(res);

        if(mn > result){
            mn = result;
            sMN = to_string(result);
            if(res[0] == 0) sMN.insert(0, "0");
        }
        if(mx < result){
            mx = result;
            sMX = to_string(result);
            if(res[0] == 0) sMX.insert(0, "0");
        }
        return;
    }

    for(int i=0; i<=9; i++){
        if(isUsed[i]) continue;

        bool check = true;
        if(res.size() != 0){
            int idx = res.size() - 1;
            if(eq[idx] == '<' && res[idx] >= i) check = false;
            else if(eq[idx] == '>' && res[idx] <= i) check = false;
        }
        if(!check) continue;

        isUsed[i] = true;
        res.push_back(i);

        func(res);

        isUsed[i] = false;
        res.pop_back();
    }
}



int main(){

    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> k;
    for(int i=0; i<k; i++) cin >> eq[i];

    func({});

    cout << sMX << "\n" << sMN;
}