#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
using namespace std;
int main()
{
    int n;
    stack<int> s;
    vector<int> v, ans;
    cin >> n;
    for (int i = 0, temp; i < n; i++)
    {
        cin >> temp;
        v.push_back(temp);
    }

    for (int i = n - 1; i >= 0; i--)
    {
        if (s.empty())
        {
            ans.push_back(-1);
            s.push(v[i]);
        }
        else
        {
            for (; !s.empty() && s.top() <= v[i]; s.pop())
                ;
            if (s.empty())
            {
                ans.push_back(-1);
            }
            else
            {
                ans.push_back(s.top());
            }
            s.push(v[i]);
        }
    }
    for (int i = n - 1; i >= 0; i--)
    {
        cout << ans[i] << " ";
    }
}