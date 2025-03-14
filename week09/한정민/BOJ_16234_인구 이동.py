from collections import deque

N, L, R = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(N)]

# 연합(union) 찾는 함수
def bfs(x, y, n, l, r, graph, visited):
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    queue = deque([(x, y)])
    union = [(x, y)]
    visited[x][y] = True
    total_pooulation = graph[x][y]
    count = 1

    while queue:
        cx, cy = queue.popleft()

        for dx, dy in directions:
            nx, ny = cx + dx, cy + dy
            if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny]:
                if l <= abs(graph[cx][cy] - graph[nx][ny]) <= r:
                    queue.append((nx, ny))
                    visited[nx][ny] = True
                    union.append((nx, ny))
                    total_pooulation += graph[nx][ny]
                    count += 1

    if count > 1:
        new_population = total_pooulation // count
        for ux, uy in union:
            graph[ux][uy] = new_population
        return True # 인구 이동 발생

    return False # 인구 이동 없음

def move(n, l, r, graph):
    days = 0

    while True:
        visited = [[False] * n for _ in range(n)]
        movement = False

        for i in range(n):
            for j in range(n):
                if not visited[i][j]:
                    if bfs(i, j, n, l, r, graph, visited):
                        movement = True
        
        if not movement:
            break

        days += 1

    return days

print(move(N, L, R, graph))