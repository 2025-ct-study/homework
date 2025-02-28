#include <bits/stdc++.h>
using namespace std;

#define ll long long

int n;
vector<int> liq1;
vector<int> liq2;

int ans1, ans2;
ll mn = 100000000000009; 


int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n;

    int t;
    for(int i=0; i<n; i++){
        cin >> t;
        if(t < 0) liq1.push_back(t);
        else liq2.push_back(t);
    }

    sort(liq1.begin(), liq1.end());
    sort(liq2.begin(), liq2.end());

    // 산성 확인
    if(liq1.size() >= 2){
        ll res = liq1[liq1.size()-1] + liq1[liq1.size()-2];
        if(abs(res) < mn){
            ans1 = liq1[liq1.size()-1];
            ans2 = liq1[liq1.size()-2];
            mn = abs(res);
        }
    }
    

    // 알칼리 확인
    if(liq2.size() >= 2){
        ll res = liq2[0] + liq2[1];
        if(abs(res) < mn){
            ans1 = liq2[0];
            ans2 = liq2[1];
            mn = abs(res);
        }
    }
    

    // 산성 하나 알칼리 하나인 경우
    int ptr1 = liq1.size()-1;
    int ptr2 = 0;
    while(ptr1 >= 0 && ptr2 < liq2.size()){
        ll res = liq1[ptr1] + liq2[ptr2];
        if(res == 0){
            ans1 = liq1[ptr1];
            ans2 = liq2[ptr2];
            break;
        }

        if(abs(res) < mn){
            ans1 = liq1[ptr1];
            ans2 = liq2[ptr2];
            mn = abs(res);
        }
        
        if(res < 0){
            ptr2++;
        }
        else if(res > 0){
            ptr1--;
        }
    }

    if(ans1 > ans2) swap(ans1, ans2);
    cout << ans1 << " " << ans2;


    return 0;
}