S = input()
N = int(input())
A = [input() for _ in range(N)]

n = len(S)
dp = [False] * (n + 1)
dp[0] = True

for i in range(1, n + 1):
    for word in A:
        if i >= len(word) and dp[i - len(word)] and S[i - len(word):i] == word:
            dp[i] = True
            break

if dp[n] == True:
    print(1)
else:
    print(0)