#include <bits/stdc++.h>
using namespace std;

// 증설된 유효기간 확인
int server[25]; // [1] = 반납까지 하루 남은 서버의 수
int solution(vector<int> players, int m, int k)
{
    int answer = 0;
    int sum_server = 0;
    for (const int &cur : players)
    {
        int new_server = 0;
        if (cur >= sum_server * m + m)
        { // 증설 필요
            new_server = (cur / m) - sum_server;
        }

        server[k] = new_server;
        // 서버 수명 업데이트
        for (int i = 1; i <= k; i++)
        {
            server[i - 1] = server[i];
        }
        sum_server = new_server - server[0] + sum_server;

        // 증설 횟수 업데이트
        answer += new_server;
        // cout << answer << ' ';
    }

    return answer;
}