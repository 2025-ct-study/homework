import heapq

N, M = map(int, input().split())
students = [sorted(list(map(int, input().split()))) for _ in range(N)]

heap = []
max_value = 0
idx = [0] * N 

for i in range(N):
    heapq.heappush(heap, (students[i][0], i, 0))
    max_value = max(max_value, students[i][0])

min_diff = float('inf')

while True:
    min_value, class_idx, student_idx = heapq.heappop(heap)
    min_diff = min(min_diff, max_value - min_value)

    if student_idx == M - 1:
        break

    next_value = students[class_idx][student_idx + 1]
    heapq.heappush(heap, (next_value, class_idx, student_idx + 1))
    max_value = max(max_value, next_value)

print(min_diff)