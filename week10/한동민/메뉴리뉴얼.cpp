#include <bits/stdc++.h>

using namespace std;

// 요리 개수 별로 관리
// course <= 20
// 최소 2명에게 추천받아야 함
unordered_map<string, int> mmap;
string tmp;
vector<string> res[11];

bool visited[11];
void mmapinsert(string &in, int idx, const int &size)
{
    // if(idx >= in.size()) return;
    if (size == tmp.size())
    {
        mmap[tmp]++;
        return;
    }

    for (int i = idx + 1; i < in.size(); i++)
    {
        if (!visited[i])
        {
            visited[i] = true;
            tmp.push_back(in[i]);
            mmapinsert(in, i, size);
            tmp.pop_back();
            visited[i] = false;
        }
    }
}
vector<string> solution(vector<string> orders, vector<int> course)
{
    for (string &order : orders)
    {
        sort(order.begin(), order.end());
        for (int i = 2; i <= order.size(); i++)
            mmapinsert(order, -1, i);
    }
    vector<string> ans;
    for (const int &i : course)
    {
        int cnt = 2;
        for (const auto &it : mmap)
        {
            if (it.first.size() != i || it.second < cnt)
                continue;
            if (it.second == cnt)
            {
                res[i].push_back(it.first);
            }
            else
            {
                res[i].clear();
                cnt = it.second;
                res[i].push_back(it.first);
            }
        }
        for (string &str : res[i])
        {
            ans.push_back(str);
        }
    }
    sort(ans.begin(), ans.end());
    return ans;
}