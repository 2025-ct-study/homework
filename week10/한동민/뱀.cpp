#include <bits/stdc++.h>
#define SIZE 100
using namespace std;

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

int mmap[101][101]; // 0(empty), 1(apple), 2(snack)
int dirInfo[10001]; // 1, 2, 3, 4
queue<pair<int, int>> q;
int main()
{
    ios_base ::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    // freopen("input.txt", "r", stdin);

    int N, K, L;
    cin >> N >> K;
    for (int i = 0, r, c; i < K; i++)
    {
        cin >> r >> c;
        mmap[r - 1][c - 1] = 1;
    }
    cin >> L;
    for (int i = 0, x; i < L; i++)
    {
        char d;
        cin >> x >> d;
        if (d == 'L')
        {
            dirInfo[x] = -1;
        }
        else
        {
            dirInfo[x] = 1;
        }
    }
    int dir = 0, t = 0;
    pair<int, int> cur = {0, 0};
    mmap[0][0] = 2;
    q.push(cur);
    while (true)
    {
        t++;
        if (t >= 10000)
            continue;
        else if (dirInfo[t - 1] > 0)
            dir = (dir + 1) % 4;
        else if (dirInfo[t - 1] < 0)
            dir = dir == 0 ? 3 : dir - 1;
        int cx = cur.first + dx[dir];
        int cy = cur.second + dy[dir];
        if (cx < 0 || cx >= N || cy < 0 || cy >= N)
        {
            break;
        }
        else if (mmap[cy][cx] == 2)
            break;
        cur = {cx, cy};
        q.push(cur);
        if (mmap[cy][cx] == 1)
        {
            mmap[cy][cx] = 0;
        }
        else
        {
            pair<int, int> tail = q.front();
            mmap[tail.second][tail.first] = 0;
            q.pop();
        }
        mmap[cy][cx] = 2;
    }
    cout << t;
    return 0;
}