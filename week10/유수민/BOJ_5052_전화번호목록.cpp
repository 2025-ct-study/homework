#include <bits/stdc++.h>
using namespace std;

int ROOT = 1;
int unused = 2;

const int MX = 10000 * 10 + 1;

int nxt[MX][11];
bool chk[MX]; // 끝을 나타냄

int c2i(char c){
    return c - '0';
}


bool find(string& str){
    int cur = ROOT;
    for(char s : str){
        cur = nxt[cur][c2i(s)];
    }
    for(int i=0; i<10; i++){
        if(nxt[cur][i] != -1) return true;
    }
    return false;
} 

void insert(string& str){

    int cur = ROOT;
    for(char s : str){
        if(nxt[cur][c2i(s)] == -1){
            nxt[cur][c2i(s)] = unused++;
        }
        cur = nxt[cur][c2i(s)];
    }
    chk[cur] = true;
}



int main(){

    int T;
    cin >> T;

    vector<string> answer;
    

    while(T--){
        int n;
        cin >> n;
        ROOT = 1;
        unused = 2;
        fill(chk, chk+MX, false);
        for(int i=0; i<MX; i++) fill(nxt[i], nxt[i]+11, -1);

        vector<string> phones;
        for(int i=0; i<n; i++){
            string str;
            cin >> str;
            phones.push_back(str);
            insert(str);
        }

        sort(phones.begin(), phones.end());

        bool checker = false;
        for(string& phone : phones){
            if(find(phone)) {
                checker = true;
            }
        }

        if(checker) answer.push_back("NO");
        else answer.push_back("YES");
        
    }

    for(string& a : answer) cout << a << "\n";
}