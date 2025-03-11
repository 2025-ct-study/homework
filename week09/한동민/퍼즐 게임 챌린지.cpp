#include <bits/stdc++.h>
using namespace std;

bool isokay(const vector<int> &diffs, const vector<int> &times, const long long &limit, int val)
{

    int size = diffs.size();
    long long sum = val >= diffs[0] ? times[0] : times[0] * (diffs[0] - val);
    for (int i = 1; i < size && sum <= limit; i++)
    {

        if (val >= diffs[i])
        { // 풀 수 있음
            sum += times[i];
        }
        else
        { // 못품
            sum += (times[i] + times[i - 1]) * (diffs[i] - val) + times[i];
        }
    }
    return (sum <= limit) ? true : false;
}
int solution(vector<int> diffs, vector<int> times, long long limit)
{

    // 이진탐색
    int left = 0;
    int right = 100000;
    while (left < right)
    {
        int mid = right - (right - left) / 2;
        if (isokay(diffs, times, limit, mid))
        { //
            right = mid - 1;
        }
        else
        {
            left = mid;
        }
    }

    return right + 1;
}