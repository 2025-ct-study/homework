import sys
from collections import deque
from itertools import combinations

input = sys.stdin.readline

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
def bfs(virus):
    queue = deque(virus)
    visited = [[-1] * n for _ in range(n)]
    empty_count = total_empty
    m = 0

    for x, y in virus:
        visited[x][y] = 0

    while queue:
        x, y = queue.popleft()
        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]
            if 0 <= nx < n and 0 <= ny < n and visited[nx][ny] == -1:
                if graph[nx][ny] == 0:
                    visited[nx][ny] = visited[x][y] + 1
                    queue.append((nx, ny))
                    m = max(visited[x][y] + 1, m)
                    empty_count -= 1
                elif graph[nx][ny] == 2:
                    visited[nx][ny] = visited[x][y] + 1
                    queue.append((nx, ny))

    return m if empty_count == 0 else float('inf')

n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)] 
viruses = []
total_empty = 0

for i in range(n):
    for j in range(n):
        if graph[i][j] == 2:
            viruses.append((i, j))
        elif graph[i][j] == 0:
            total_empty += 1

answer = float('inf')
for virus in combinations(viruses, m):
    answer = min(bfs(virus), answer)

if answer == float('inf'):
    print(-1)
else:
    print(answer)