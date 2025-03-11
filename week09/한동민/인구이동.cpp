#include <bits/stdc++.h>
using namespace std;

int people[50][50];
int N, L, R;
bool visited[50][50];
bool ismove = true;
int dx[4] = {0, 0, -1, 1};
int dy[4] = {-1, 1, 0, 0};
int cnt = 0;
void bfs(int c, int r)
{
    if (visited[r][c])
        return;
    visited[r][c] = true;
    queue<pair<int, int>> q;
    queue<pair<int, int>> checkq;
    q.push(make_pair(c, r));
    checkq.push(make_pair(c, r));
    int popsum = 0;
    popsum += people[r][c];
    int country = 0;
    country++;
    while (!q.empty())
    {
        int x, y;
        x = q.front().first;
        y = q.front().second;
        q.pop();
        for (int i = 0; i < 4; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];
            int sub = abs(people[y][x] - people[ny][nx]);
            if (nx >= 0 && nx < N && ny >= 0 && ny < N)
            {
                if (sub >= L && sub <= R && !visited[ny][nx])
                {
                    visited[ny][nx] = true;
                    country++;
                    popsum += people[ny][nx];
                    ismove = true;
                    q.push(make_pair(nx, ny));
                    checkq.push(make_pair(nx, ny));
                }
            }
        }
    }
    // 인구 이동
    while (!checkq.empty())
    {
        int qx = checkq.front().first;
        int qy = checkq.front().second;
        people[qy][qx] = popsum / country;
        checkq.pop();
    }
}
int main()
{
    cin >> N >> L >> R;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            cin >> people[i][j];
        }
    }
    while (ismove)
    {
        ismove = false;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                bfs(j, i);
            }
        }
        if (!ismove)
            break;
        cnt++;
        memset(visited, 0, sizeof(visited));
    }
    cout << cnt;
    return 0;
}
