

#include <bits/stdc++.h>
#define SIZE 125
#define INF 987654321
#define pipii pair<int, pair<int, int>>
using namespace std;
int mmap[SIZE][SIZE];
int dist[SIZE][SIZE];
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};
int search(const int &N)
{
    priority_queue<pipii> pq; // cost, 좌표
    dist[0][0] = mmap[0][0];
    pq.push({-mmap[0][0], {0, 0}});
    while (!pq.empty())
    {
        pipii cur = pq.top();
        pq.pop();
        if (cur.second.first == N - 1 && cur.second.second == N - 1)
        {
            return -cur.first;
        }
        int x = cur.second.first;
        int y = cur.second.second;
        int cost = -cur.first;
        for (int i = 0; i < 4; i++)
        {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                continue;
            if (dist[ny][nx] <= cost + mmap[ny][nx])
                continue;
            dist[ny][nx] = dist[y][x] + mmap[ny][nx];
            pq.push({-dist[ny][nx], {nx, ny}});
        }
    }
    return dist[N - 1][N - 1];
}
int main()
{
    ios_base ::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    // freopen("input.txt", "r", stdin);
    int t = 1;
    while (true)
    {
        int N;
        cin >> N;
        if (!N)
            break;

        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                mmap[i][j] = 0;
                dist[i][j] = INF;
            }
        }

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                cin >> mmap[i][j];
            }
        }
        cout << "Problem " << t << ": " << search(N) << '\n';
        t++;
    }
    return 0;
}
