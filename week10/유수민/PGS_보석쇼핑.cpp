#include <bits/stdc++.h>
using namespace std;

const int MX = 100000;
map<string, int> dir;
int cnt[MX+1];



vector<int> solution(vector<string> gems) {
    
    int number = 0;
    for(const string& g : gems){
        if(dir[g] == 0){
            dir[g] = ++number;
        }
    }

    
    int ans1 = 0, ans2 = gems.size()-1;
    int mnLen = gems.size();
    
    int left = 0, right = 0;
    int cur = 1;
    cnt[dir[gems[left]]] = 1;
    
    // cout << gems[left] << " " << dir[gems[left]] << " " << cnt[dir[gems[left]]];
    
    while(left <= right && right < gems.size()){
        
        if(right == gems.size()) break;
        
        if(cur >= number){
            
            if(mnLen > (right-left+1)){
                mnLen = right-left+1;
                ans1 = left;
                ans2 = right;
            }
            
            cnt[dir[gems[left]]]--;
            if(cnt[dir[gems[left]]] == 0) cur--;
            left++;
            
        }
        else {
            right++;
            if(right >= gems.size()) continue;
            cnt[dir[gems[right]]]++;
            if(cnt[dir[gems[right]]] == 1) cur++;
        }
    }
    
    
    vector<int> answer;
    answer.push_back(ans1+1);
    answer.push_back(ans2+1);
    return answer;
}