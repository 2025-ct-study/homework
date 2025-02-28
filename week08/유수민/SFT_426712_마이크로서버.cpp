#include<bits/stdc++.h>
using namespace std;

int n;
int m[100001];

int main(int argc, char** argv)
{

    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);


    int tc;
    vector<int> answer;
    cin >> tc;

    while(tc--){
        cin >> n;
        vector<int> service(n);
        for(int i=0; i<n; i++){
            cin >> service[i];
        }
        sort(service.begin(), service.end(), greater<int>());

        priority_queue<int> pq;
        int cnt = 0;
        
        for(const int& s : service){

            if(pq.empty()){
                cnt++;
                pq.push(900 - s);
            }
            else{
                if(pq.top() < s){
                    cnt++;
                    pq.push(900 - s);
                }
                else{
                    int tp = pq.top();
                    pq.pop();
                    pq.push(tp - s);
                }
            }
        }
        answer.push_back(cnt);
    }

    for(const int& a : answer) cout << a << "\n";

    return 0;
}