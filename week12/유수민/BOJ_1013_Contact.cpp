#include <bits/stdc++.h>
using namespace std;

// c++에서는 정규표현식을 사용할 수 있음
int T;
string s;
regex pattern("(100+1+|01)+");

int main(){

    cin >> T;

    while(T--) {
        cin >> s;
        cout << (regex_match(s, pattern) ? "YES\n" : "NO\n");
    }

    
}