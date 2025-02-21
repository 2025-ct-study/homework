#include <bits/stdc++.h>
using namespace std;

int n;
int score[21][21];
int answer = INT_MAX;
bool team1[20];
bool team2[20];

void calcScore(vector<int> member){

    fill(team1, team1+20, false);
    fill(team2, team2+20, false);
    for(auto m : member){
        team1[m] = true;
    }
    for(int i=1; i<=n; i++){
        if(!team1[i]) team2[i] = true;
    }

    int sum1 = 0;
    int sum2 = 0;

    for(int i=1; i<=n; i++){
        for(int j=1; j<i; j++){
            if(i == j) continue;
            if(team1[i] && team1[j]){
                sum1 += score[i][j];
                sum1 += score[j][i];
            }
            else if(team2[i] && team2[j]){
                sum2 += score[i][j];
                sum2 += score[j][i];
            }
        }
    }

    answer = min(answer, abs(sum1-sum2));
}

void makeTeam(vector<int> member, int idx){

    if(idx > n){
        if(member.size() != 0 && member.size() != n) calcScore(member);
        return;
    }
    makeTeam(member, idx+1);
    member.push_back(idx);
    makeTeam(member, idx+1);
}

int main(){
    
    cin >> n;

    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            cin >> score[i][j];
        }
    }

    vector<int> temp;
    makeTeam(temp, 1);

    cout << answer;
}