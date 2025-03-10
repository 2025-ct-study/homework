#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    string str;
    cin >> str;
    int n;
    cin >> n;
    vector<string> dic(n);
    for (int i = 0; i < n; i++) {
        cin >> dic[i];
    }
    
    int strSize = str.size();
    vector<bool> visited(strSize + 1, false); 
    queue<int> q;
    q.push(0);
    visited[0] = true;
    
    while (!q.empty()) {
        int index = q.front();
        q.pop();
        
        if (index == strSize) {  // 문자열 끝까지 도달
            cout << 1;
            return 0;
        }
        
        for (const string &word : dic) {
            int len = word.size();
            if (index + len > strSize) continue; // 범위
            
            if (str.compare(index, len, word) == 0) {
                if (!visited[index + len]) {
                    visited[index + len] = true;
                    q.push(index + len);
                }
            }
        }
    }
    
    cout << 0;
    return 0;
}
