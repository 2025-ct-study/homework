import sys
input = sys.stdin.readline

T = int(input())
for _ in range(T):
    N = int(input())
    services = list(map(int, input().split()))
    services.sort()

    count_300 = 0
    for i in range(N):
        if services[i] == 300:
            count_300 += 1

    start = count_300
    end = N - 1
    count = 0

    while start <= end:
        count += 1
        if services[end] > 600:
            pass
        elif start < end and services[start] + services[end] <= 900:
            start += 1
        elif count_300 > 0:
            count_300 -= 1
        end -= 1

    count += (count_300 + 2) // 3 # 남은 300을 최소 서버로 묶음음
    print(count)