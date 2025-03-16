

#include <bits/stdc++.h>
using namespace std;
int main()
{
    ios_base ::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    // freopen("input.txt", "r", stdin);
    int t;
    cin >> t;

    while (t--)
    {
        int n;
        bool flag = true;
        vector<string> vec;
        unordered_map<string, int> mmap;
        cin >> n;
        for (int i = 0; i < n; i++)
        {
            string tmp;
            cin >> tmp;
            vec.emplace_back(tmp);
            mmap.insert({tmp, 1});
        }

        for (int i = 0; i < n && flag; i++)
        {
            for (int s = 1; s < vec[i].length() && flag; s++)
            {
                string tmp = vec[i].substr(0, s);
                if (mmap.find(tmp) != mmap.end()) // 존재
                {
                    flag = false;
                    break;
                }
            }
        }
        if (flag)
            cout << "YES" << '\n';
        else
            cout << "NO" << '\n';
    }

    return 0;
}