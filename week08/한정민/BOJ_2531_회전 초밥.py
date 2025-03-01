import sys
from collections import defaultdict

input = sys.stdin.readline
n, d, k, c = map(int, input().split())
sushi = [int(input()) for _ in range(n)]
sushi.extend(sushi)

d = defaultdict(int)
start, end = 0, 0
result = 0

while end < k:
    d[sushi[end]] += 1
    end += 1
d[c] += 1 # 쿠폰 초밥 추가

while end < len(sushi):
    result = max(result, len(d))
    d[sushi[start]] -= 1
    if d[sushi[start]] == 0:
        del d[sushi[start]]
    d[sushi[end]] += 1
    start += 1
    end += 1

print(result)