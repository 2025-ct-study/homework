from collections import deque

def solution(maps):
    answer = []
    n = len(maps)
    m = len(maps[0])
    
    visited = [[False] * m for _ in range(n)]
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    
    for i in range(n):
        for j in range(m):
            if maps[i][j] != 'X' and not visited[i][j]:
                queue = deque()
                queue.append((i, j))
                visited[i][j] = True
                total = int(maps[i][j])
                
                while queue:
                    x, y = queue.popleft()
                    for dx, dy in directions:
                        nx, ny = x + dx, y + dy
                        if 0 <= nx < n and 0 <= ny < m:
                            if maps[nx][ny] != 'X' and not visited[nx][ny]:
                                visited[nx][ny] = True
                                queue.append((nx, ny))
                                total += int(maps[nx][ny])
                answer.append(total)
    
    return sorted(answer) if answer else [-1]