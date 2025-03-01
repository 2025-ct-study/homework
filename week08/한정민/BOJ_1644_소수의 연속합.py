N = int(input())

a = [False, False] + [True] * (N-1)
primes=[]
for i in range(2, N+1):
    if a[i]:
        primes.append(i)
        for j in range(2*i, N+1, i):
            a[j] = False

start, end = 0, 0
count = 0
while end < len(primes):
    temp = sum(primes[start:end + 1])
    if temp < N:
        end += 1
    elif temp > N:
        start += 1
    else:
        count += 1
        end += 1

print(count)