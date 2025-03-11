#include <bits/stdc++.h>
using namespace std;

// 9___
// 8765
// 1234
int solution(int n, int w, int num)
{

    int ans = 0;
    int cur_layer, serial;
    if (num % w)
        cur_layer = (num / w) + 1;
    else
        cur_layer = num / w;
    if (cur_layer % 2 == 0)
    {
        serial = (num % w) ? (w - (num % w) + 1) : 1;
    }
    else
    {
        serial = num % w ? num % w : w;
    }

    // cout << cur_layer << ' ' << serial;
    for (int cur = num; cur <= n; ans++, cur_layer++)
    {
        if (cur_layer % 2 == 0)
        { // 짝
            cur += (serial - 1) * 2 + 1;
        }
        else
        { // 홀
            cur += (w - serial) * 2 + 1;
        }
        // cout << cur << ' ' << ans << ' ' << cur_layer << '\n';
    }
    return ans;
}