#include <bits/stdc++.h>
using namespace std;

#define ll long long


bool check(int level, vector<int>& diffs, vector<int>& times, long long limit){
    
    ll sum = 0;
    for(int i=0; i<diffs.size(); i++){
        if(diffs[i] <= level){
            sum += times[i];
        }
        else{
            int counts = diffs[i] - level;
            sum += (counts * (times[i] + times[i-1]) + times[i]);
        }
    }
    
    
    return sum <= limit;
}

// 숙련도의 최소값
int solution(vector<int> diffs, vector<int> times, long long limit) {

    int st = 100001;
    int en = 1;
    
    for(int i=0; i<diffs.size(); i++) {
        st = min(st, diffs[i]);
        en = max(en, diffs[i]);
    }
    
    
    int answer = 0;
    while(st <= en){
        int mid = (st + en) / 2;
        if(check(mid, diffs, times, limit)){
            answer = mid;
            en = mid - 1;
        }
        else{
            st = mid + 1;
        }
    }

    
    return answer;
}