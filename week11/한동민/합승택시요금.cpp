#include <bits/stdc++.h>
#define INF 987654321

using namespace std;

// start -> 중간지점
// 중간지점 -> A의 지점
// 중간지점 -> B의 지점
// 3개의 합

int dst[201][201];
void Init()
{
    for (int i = 0; i <= 200; i++)
        for (int j = 0; j <= 200; j++)
        {
            dst[i][j] = INF;
            if (i == j)
                dst[i][j] = 0;
        }
}
int solution(int n, int s, int a, int b, vector<vector<int>> fares)
{
    Init();
    for (const vector<int> &v : fares)
    {
        dst[v[0]][v[1]] = dst[v[1]][v[0]] = v[2];
    }
    for (int k = 1; k <= n; k++)
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
            {
                if (dst[i][k] == INF || dst[k][j] == INF || i == j)
                    continue;
                if (dst[i][j] > dst[i][k] + dst[k][j])
                    dst[i][j] = dst[i][k] + dst[k][j];
            }
    int ans = INF;
    for (int mid = 1; mid <= n; mid++)
    {
        if (dst[s][mid] == INF || dst[mid][a] == INF || dst[mid][b] == INF)
            continue;
        ans = min(ans, dst[s][mid] + dst[mid][a] + dst[mid][b]);
    }
    return ans;
}