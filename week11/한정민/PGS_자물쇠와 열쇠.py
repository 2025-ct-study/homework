# 2차원 배열 회전
def rotate(key, d):
    n = len(key)
    result = [[0] * n for _ in range(n)]
    
    if d % 4 == 1: # 90도 회전
        for r in range(n):
            for c in range(n):
                result[c][n-r-1] = key[r][c]
    elif d % 4 == 2: # 180도 회전
        for r in range(n):
            for c in range(n):
                result[n-r-1][n-c-1] = key[r][c]
    elif d % 4 == 3: # 270도 회전
        for r in range(n):
            for c in range(n):
                result[n-c-1][r] = key[r][c]
    else:
        for r in range(n):
            for c in range(n):
                result[r][c] = key[r][c]
    
    return result
    
# 자물쇠 부분 모두 1인지 확인
def check(lock):
    n = len(lock) // 3
    for i in range(n, n*2):
        for j in range(n, n*2):
            if lock[i][j] != 1:
                return False
    return True
    
def solution(key, lock):
    m = len(key)
    n = len(lock)
    temp_lock = [[0] * (n*3) for _ in range(n*3)]
    
    # 확장한 lock의 중앙 부분에 기존 lock 넣기
    for i in range(n):
        for j in range(n):
            temp_lock[n+i][n+j] = lock[i][j]
    
    for i in range(1, n*2):
        for j in range(1, n*2):
            for d in range(4):
                rotate_key = rotate(key, d)
                for x in range(m):
                    for y in range(m):
                        temp_lock[i+x][j+y] += rotate_key[x][y]
                
                if check(temp_lock):
                    return True
                
                for x in range(m):
                    for y in range(m):
                        temp_lock[i+x][j+y] -= rotate_key[x][y]
                        
    return False