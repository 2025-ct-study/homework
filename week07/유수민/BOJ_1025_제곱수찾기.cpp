#include <bits/stdc++.h>
using namespace std;

int n, m;
int arr[10][10];
bool number[10];
int answer = -1;
vector<int> sub1; // n행
vector<int> sub2; // m행

// 완전 제곱수 판단
bool isComp(int num){
    if(num  == (int)sqrt(num) * (int)sqrt(num)) {
        answer = max(answer, num);
        return true;
    }
    else return false;
}

// 숫자 만들기
int makeNum(vector<int> seq){
    int res = 0;

    int sz = seq.size();
    int ten = 1;
    for(int i=sz-1; i>=0; i--){
        res += seq[i] * ten;
        ten *= 10;
    }
    return res;
}


void find(int x, int y, int k, int l, vector<int> seq){

    if(k == 0 && l == 0) return;
    if(seq[0] == 0) return;
    int num = makeNum(seq);
    isComp(num);

    if(x+k < 1 || x+k > n || y+l < 1 || y+l > m) return;
    seq.push_back(arr[x+k][y+l]);
    
    find(x+k, y+l, k, l, seq);
}



int main(){

    cin >> n >> m;
    for(int i=1; i<=n; i++){
        string str;
        cin >> str;
        for(int j=0; j<m; j++){
            arr[i][j+1] = str[j] - '0';
            number[str[j]-'0'] = true;
        }
    }
    
    if(number[0]) answer = 0;
    if(number[1]) answer = 1;
    if(number[4]) answer = 4;
    if(number[9]) answer = 9;

    for(int i=0; i<n; i++){
        sub1.push_back(i);
        if(i != 0) sub1.push_back(i * -1);
    }
    sort(sub1.begin(), sub1.end());
    for(int i=0; i<m; i++){
        sub2.push_back(i);
        if(i != 0) sub2.push_back(i * -1);
    }
    sort(sub2.begin(), sub2.end());

    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            
            for(int k=0; k<sub1.size(); k++){
                for(int l=0; l<sub2.size(); l++){
                    
                    vector<int> temp;
                    temp.push_back(arr[i][j]);
                    find(i, j, sub1[k], sub2[l], temp);
                }
            }         
        }
    }

    cout << answer;
}