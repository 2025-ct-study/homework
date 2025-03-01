import sys
input = sys.stdin.readline
n, k = map(int, input().split())
vertical = [0] * 1000002

for _ in range(n):
    start, end = map(int, input().split())
    vertical[start + 1] += 1
    vertical[end + 1] -= 1

# 누적합 계산
for i in range(1, len(vertical)):
    vertical[i] += vertical[i-1]

left, right, total = 0, 0, 0
flag = False

while right < 1000001:
    if total == k:
        flag = True
        break
    elif total < k:
        right += 1
        total += vertical[right]
    else:
        left += 1
        total -= vertical[left]

if flag:
    print(left, right)
else:
    print(0, 0)