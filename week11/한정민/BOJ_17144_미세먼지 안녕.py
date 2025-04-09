r, c, t = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(r)]

cleaner = []
for i in range(r):
    if board[i][0] == -1:
        cleaner.append(i)

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

# 미세먼지 확산
def spread():
    spread_board = [[0] * c for _ in range(r)]

    for i in range(r):
        for j in range(c):
            if board[i][j] > 0:
                amount = board[i][j] // 5
                count = 0
                for d in range(4):
                    ni, nj = i + dx[d], j + dy[d]
                    if 0 <= ni < r and 0 <= nj < c and board[ni][nj] != -1:
                        count += 1 
                        spread_board[ni][nj] += amount
                board[i][j] -= amount * count

    for i in range(r):
        for j in range(c):
            board[i][j] += spread_board[i][j]

# 공기청정기 작동
def clean():

    up = cleaner[0]
    # 윗부분 반시계방향
    for i in range(up-2, -1, -1): # 위에서 아래
        board[i+1][0] = board[i][0]
    for i in range(1, c): # 오른쪽에서 왼쪽
        board[0][i-1] = board[0][i]
    for i in range(1, up+1): # 아래에서 위
        board[i-1][-1] = board[i][-1]
    for i in range(c-2, 0, -1): # 왼쪽에서 오른쪽
        board[up][i+1] = board[up][i]
    board[up][1] = 0

    down = cleaner[1]
    # 아랫부분 시계방향
    for i in range(down+2, r): # 아래에서 위
        board[i-1][0] = board[i][0]
    for i in range(1, c): # 위에서 아래
        board[-1][i-1] = board[-1][i]
    for i in range(r-2, down-1, -1): # 오른쪽에서 왼쪽
        board[i+1][-1] = board[i][-1]
    for i in range(c-2, 0, -1): # 왼쪽에서 오른쪽
        board[down][i+1] = board[down][i]
    board[down][1] = 0

for _ in range(t):
    spread()
    clean()

total = 0
for i in range(r):
    for j in range(c):
        if board[i][j] > 0 :
            total += board[i][j]
print(total)