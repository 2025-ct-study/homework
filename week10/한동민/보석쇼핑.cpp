#include <bits/stdc++.h>

using namespace std;
unordered_map<string, int> mmap;
int num_size = 0;
vector<int> solution(vector<string> gems)
{

    for (string cur : gems)
    {
        if (mmap.find(cur) == mmap.end())
        {
            num_size++;
            mmap.insert({cur, 0});
        }
    }
    int max_r = gems.size();
    int l = 0, r = 0;
    int cnt = 1;
    vector<int> ans;
    ans.resize(2);
    ans[1] = 100000;
    ans[0] = 0;
    mmap[gems[0]]++;
    while (l <= r)
    {
        if (r == max_r - 1)
        {
            if (cnt == num_size)
            {
                while (mmap[gems[l]] > 1)
                {
                    mmap[gems[l]]--;
                    l++;
                }
                if (ans[1] - ans[0] > r - l)
                {
                    ans[1] = r + 1;
                    ans[0] = l + 1;
                }
            }
            break;
        }
        if (cnt < num_size)
        { // 오른쪽으로 이동
            r++;
            if (mmap[gems[r]] == 0)
                cnt++;
            mmap[gems[r]]++;
        }
        else
        { // 왼쪽으로 이동
            while (mmap[gems[l]] > 1)
            {
                mmap[gems[l]]--;
                l++;
            }
            if (ans[1] - ans[0] > r - l)
            {
                ans[1] = r + 1;
                ans[0] = l + 1;
            }
            mmap[gems[l]]--;
            l++;
            cnt--;
        }
    }

    return ans;
}