#include <bits/stdc++.h>
using namespace std;

int n, m;

bool isDone(const vector<vector<int>> &current, const vector<vector<int>> &target) {
    for (int i = 0; i < n; i++){
        for (int j = 0; j < m; j++){
            if (current[i][j] != target[i][j])
                return false;
        }
    }
    return true;
}

int solution(vector<vector<int>> beginning, vector<vector<int>> target) {
    n = beginning.size();
    m = beginning[0].size();
    int total = 1 << (n + m); // 2^(n+m)
    int answer = INT_MAX;
    
  
    for (int mask = 0; mask < total; mask++){
        vector<vector<int>> curr = beginning;
        int flips = 0;
        
        for (int i = 0; i < n + m; i++){
            
            if (mask & (1 << i)) {
                flips++;
                if (i < n) {
                    for (int j = 0; j < m; j++){
                        curr[i][j] = 1 - curr[i][j];
                    }
                } 
                else {     
                    int col = i - n;
                    for (int j = 0; j < n; j++){
                        curr[j][col] = 1 - curr[j][col];
                    }
                }
            }
        }
        if (isDone(curr, target)) {
            answer = min(answer, flips);
        }
    }
    
    return (answer == INT_MAX) ? -1 : answer;
}
