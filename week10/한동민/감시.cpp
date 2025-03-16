#include <bits/stdc++.h>
using namespace std;

int N, M;
int mmap[8][8];
vector<pair<int, int>> cctv;

int ans = 987654321;
int dx[4] = {0, -1, 0, 1};
int dy[4] = {1, 0, -1, 0};

void check(int x, int y, int dir)
{
    dir %= 4;
    while (1)
    {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        x = nx;
        y = ny;
        if (nx < 0 || ny < 0 || nx >= N || ny >= M)
            return;
        if (mmap[nx][ny] == 6)
            return;
        if (mmap[nx][ny] != 0)
            continue;
        mmap[nx][ny] = -1;
    }
}

void dfs(int idx)
{
    if (idx == cctv.size())
    {
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (!mmap[i][j])
                    cnt++;

        ans = min(ans, cnt);
        return;
    }

    int x = cctv[idx].first;
    int y = cctv[idx].second;
    int tmp[9][9];

    for (int dir = 0; dir < 4; dir++)
    {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                tmp[i][j] = mmap[i][j];

        if (mmap[x][y] == 1)
            check(x, y, dir);
        else if (mmap[x][y] == 2)
        {
            check(x, y, dir);
            check(x, y, dir + 2);
        }
        else if (mmap[x][y] == 3)
        {
            check(x, y, dir);
            check(x, y, dir + 1);
        }
        else if (mmap[x][y] == 4)
        {
            check(x, y, dir);
            check(x, y, dir + 1);
            check(x, y, dir + 2);
        }
        else if (mmap[x][y] == 5)
        {
            check(x, y, dir);
            check(x, y, dir + 1);
            check(x, y, dir + 2);
            check(x, y, dir + 3);
        }

        dfs(idx + 1);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                mmap[i][j] = tmp[i][j];
    }
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M;

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cin >> mmap[i][j];
            if (mmap[i][j] != 0 && mmap[i][j] != 6)
                cctv.push_back({i, j});
        }
    }

    dfs(0);
    cout << ans << '\n';

    return 0;
}