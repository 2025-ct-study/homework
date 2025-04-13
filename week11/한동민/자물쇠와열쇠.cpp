#include <bits/stdc++.h>

using namespace std;

// 회전가능 + 이동가능

// (1,2), (2,1)
//

void rotate(vector<vector<int>> &vec)
{                       // -90도 회전
    int n = vec.size(); // 벡터의 크기

    // 먼저 행과 열을 바꾸는 과정
    for (int i = 0; i < n; ++i)
    {
        for (int j = i; j < n; ++j)
        {
            std::swap(vec[i][j], vec[j][i]);
        }
    }
    // 각 행을 뒤집는 과정
    for (int i = 0; i < n; ++i)
    {
        std::reverse(vec[i].begin(), vec[i].end());
    }
}

bool check(vector<vector<int>> &lock)
{
    int M = lock.size();
    for (int i = 0; i < M; i++)
    {
        for (int j = 0; j < M; j++)
        {
            if (lock[i][j] == 0)
                return false;
        }
    }
    return true;
}

// M : 5 N : 3
bool check_lock(vector<vector<int>> &key, vector<vector<int>> &lock)
{
    int M = key.size();                 // 열쇠의 크기
    int N = lock.size();                // 자물쇠의 크기
    int extendedSize = N + 2 * (M - 1); // 확장된 자물쇠의 크기

    // 확장된 자물쇠 생성
    vector<vector<int>> extendedLock(extendedSize, vector<int>(extendedSize, 0));
    for (int i = M - 1; i <= N + M - 2; i++)
    {
        for (int j = M - 1; j <= N + M - 2; j++)
        {
            extendedLock[i][j] = lock[i - (M - 1)][j - (M - 1)];
        }
    }

    // 열쇠를 회전하며 모든 위치에서 맞춰보기
    for (int x = 0; x <= N + M - 2; x++)
    {
        for (int y = 0; y <= N + M - 2; y++)
        {
            // 열쇠를 넣어보기
            for (int i = 0; i < M; i++)
            {
                for (int j = 0; j < M; j++)
                {
                    extendedLock[x + i][y + j] += key[i][j];
                }
            }

            // 자물쇠가 열리는지 확인
            bool isUnlocked = true;
            for (int i = M - 1; i <= N + M - 2; i++)
            {
                for (int j = M - 1; j <= N + M - 2; j++)
                {
                    if (extendedLock[i][j] != 1)
                    {
                        isUnlocked = false;
                        break;
                    }
                }
                if (!isUnlocked)
                    break;
            }

            // 자물쇠가 열린 경우
            if (isUnlocked)
                return true;

            // 열쇠를 빼고 다음 위치로 이동
            for (int i = 0; i < M; i++)
            {
                for (int j = 0; j < M; j++)
                {
                    extendedLock[x + i][y + j] -= key[i][j];
                }
            }
        }
    }
    // 모든 경우를 다 확인했으나 열리지 않음
    return false;
}
bool solution(vector<vector<int>> key, vector<vector<int>> lock)
{
    for (int i = 0; i < 4; i++)
    {
        if (check_lock(key, lock))
        {
            return true;
        }
        rotate(key);
    }
    return false;
}