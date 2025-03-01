import sys
input = sys.stdin.readline

n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]
answer = 0
now = [n // 2, n // 2] # 현재 좌표

left = [(-2, 0, 0.02), (2, 0, 0.02), (-1, -1, 0.1), (-1, 0, 0.07), (-1, 1, 0.01), (1, -1, 0.1), (1, 0, 0.07), (1, 1, 0.01), (0, -2, 0.05), (0, -1, 0)]
right = [(x, -y, r) for x, y, r in left]
down = [(-y, x, r) for x, y, r in left]
up = [(-x, y, r) for x, y, r in down]
rate = {'left' : left, 'right' : right, 'down' : down, 'up' : up}

def tornado(count, dx, dy, direction):
    global answer
    for _ in range(count + 1):
        now[0], now[1] = now[0] + dx, now[1] + dy
        
        # 격자 밖이면 종료료
        if now[0] < 0 or now[1] < 0:
            break

        total = 0 # 퍼지는 모래 양의 누적값
        for dx, dy, r in rate[direction]:
            nx, ny = now[0] + dx, now[1] + dy
            if r == 0: # a 위치 (남은 모래)
                sand = board[now[0]][now[1]] - total
            else:
                sand = int(board[now[0]][now[1]] * r)
            
            if 0 <= nx < n and 0 <= ny < n:
                board[nx][ny] += sand
            else:
                answer += sand
            total += sand

for i in range(n):
    if i % 2 == 0:
        tornado(i, 0, -1, 'left')
        tornado(i, 1, 0, 'down')
    else:
        tornado(i, 0, 1, 'right')
        tornado(i, -1, 0, 'up')

print(answer)