#include <bits/stdc++.h>
using namespace std;

int n;
char ops[100];
bool visited[10];
vector<string> numbers;
int idx = 0;

bool check(char a, char b, char op)
{
    if (a < b && op == '<')
        return true;
    if (a > b && op == '>')
        return true;
    return false;
}

void select(int index, string num)
{
    if (index == n + 1)
    {
        numbers.push_back(num);
        idx++;
        return;
    }

    for (int i = 0; i < 10; i++)
    {
        if (visited[i])
            continue;

        if (index == 0 || check(num[index - 1], i + '0', ops[index - 1]))
        {
            visited[i] = true;
            select(index + 1, num + to_string(i));
            visited[i] = false;
        }
    }
}

int main()
{
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        cin >> ops[i];
    }
    select(0, "");

    cout << numbers[idx - 1] << "\n"
         << numbers[0];
}