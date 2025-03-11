#include <bits/stdc++.h>
using namespace std;

int n;
string s;
vector<string> strings;
bool dp[101];

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> s;
    cin >> n;

    for (int i = 0; i < n; i++)
    {
        string tmp;
        cin >> tmp;
        strings.push_back(tmp);
    }

    for (int i = 0; i < s.length(); i++)
    {
        if (dp[i] || i == 0)
        {
            int st = i;

            for (int j = 0; j < n; j++)
            {
                // 부분문자열을 합칠 때 원래 문자열보다 더 길어지는 경우는 제외
                if (st + strings[j].length() > s.length())
                    continue;

                bool flag = true;
                for (int k = 0; k < strings[j].length(); k++)
                {
                    if (strings[j][k] != s[st + k])
                    {
                        flag = false;
                        break;
                    }
                }

                if (flag)
                {
                    dp[st + strings[j].length()] = 1;
                }
            }
        }
    }

    cout << dp[s.length()];

    return 0;
}