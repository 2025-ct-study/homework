N = int(input())
arr = list(map(int, input().split()))
NGE = [-1] * N
stack = []

for i in range(N - 1, -1, -1):
    while stack:
        if stack[-1] > arr[i]:
            NGE[i] = stack[-1]
            break
        else:
            stack.pop()
    stack.append(arr[i])

for i in NGE:
    print(i, end = ' ')