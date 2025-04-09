import copy

def flip(coin):
    return 1 - coin

def flip_row(board, row_idx):
    for j in range(len(board[0])):
        board[row_idx][j] = flip(board[row_idx][j])    
    
def flip_col(board, col_idx):
    for i in range(len(board)):
        board[i][col_idx] = flip(board[i][col_idx])
        
def solution(beginning, target):
    n = len(beginning)
    m = len(beginning[0])
    answer = float('inf')
    
    for row_mask in range(1 << n):
        board = copy.deepcopy(beginning)
        flip_count = 0
        
        for i in range(n):
            if row_mask & (1 << i):
                flip_row(board, i)
                flip_count += 1
        
        for j in range(m):
            if any(board[i][j] != target[i][j] for i in range(n)):
                flip_col(board, j)
                flip_count += 1

        if board == target:
            answer = min(answer, flip_count)

    return answer if answer != float('inf') else -1