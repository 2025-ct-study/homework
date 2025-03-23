#include <bits/stdc++.h>
#define SIZE 1000000
using namespace std;
vector<vector<int>> edg;
bool visited[SIZE + 1];
void searchtress(vector<vector<int>> &trees, const vector<int> &nodes)
{
    for (const int &i : nodes)
    {
        if (visited[i])
            continue;
        visited[i] = true;
        vector<int> vec;
        queue<int> q;
        q.push(i);
        while (!q.empty())
        {
            const int &cur = q.front();
            q.pop();
            vec.emplace_back(cur);
            for (const int &nxt : edg[cur])
            {
                if (visited[nxt])
                    continue;
                q.push(nxt);
                visited[nxt] = true;
            }
        }
        trees.emplace_back(vec);
    }
}
vector<int> solution(vector<int> nodes, vector<vector<int>> edges)
{

    edg.resize(SIZE + 1);
    for (const vector<int> edge : edges)
    {
        edg[edge[0]].emplace_back(edge[1]);
        edg[edge[1]].emplace_back(edge[0]);
    }
    vector<vector<int>> trees;
    searchtress(trees, nodes);
    vector<int> ans = {0, 0};
    for (const vector<int> &tree : trees)
    {
        int evenodd = 0;
        int reverseevenodd = 0;
        for (const int &i : tree)
        { //
            int ssize = edg[i].size() ? edg[i].size() - 1 : edg[i].size();
            if (i % 2)
            { // 홀
                if (ssize % 2)
                    evenodd++;
                else
                    reverseevenodd++;
            }
            else
            { // 짝
                if (ssize % 2)
                    reverseevenodd++;
                else
                    evenodd++;
            }
        }
        // cout << evenodd + reverseevenodd << '\n';
        if (tree.size() == 1)
        {
            if (tree[0] % 2)
                ans[1]++;
            else
                ans[0]++;
        }
        else
        {
            if (evenodd + 1 == tree.size())
            {
                ans[0]++;
                // cout << "c"<< tree[0] << '\n';
            }
            if (reverseevenodd + 1 == tree.size())
            {
                ans[1]++;
                // cout << "r" <<tree[0] << '\n';
            }
        }
    }

    return ans;
}