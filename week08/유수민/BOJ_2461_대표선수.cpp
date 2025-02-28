#include <bits/stdc++.h>
using namespace std;

const long long MX = 1000000000;

#define X first
#define Y second
#define pii pair<long long, int>

int n, m;
vector<pii> students;
int studentCnt[1001]; // 해당 학급에 있는 학생 수

// {능력치, 학급번호}

int main(){
    ios::sync_with_stdio(0);
    cout.tie(0);
    cin.tie(0);

    cin >> n >> m;
    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            int t;
            cin >> t;
            students.push_back({t, i});
        }
    }

    sort(students.begin(), students.end());

    int mn = INT_MAX;
    int mx = INT_MIN;
    long long answer = MX;
    int cur = 0;

    for(int i=0; i<n; i++){
        if(studentCnt[students[i].Y] == 0) cur++;
        studentCnt[students[i].Y]++;
    }

    int left = 0;
    int right = n-1;
    while(left <= right && right < students.size()){
        if(cur >= n){
            answer = min(answer, students[right].X-students[left].X);
            
            studentCnt[students[left].Y]--;
            if(studentCnt[students[left].Y] == 0) cur--;
            left++;
            
            continue;
        }
        

        studentCnt[students[++right].Y]++;
        if(studentCnt[students[right].Y] == 1) cur++;

    }

    cout << answer;
}