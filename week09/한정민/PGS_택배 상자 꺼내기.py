def solution(n, w, num):
    warehouse = []
    stack = []

    for i in range(1, n + 1):
        stack.append(i)
        if len(stack) == w or i == n:
            if len(stack) < w:
                while len(stack) < w:
                    stack.append(0)
            if len(warehouse) % 2 == 0:
                warehouse.append(stack[:])
            else:
                warehouse.append(stack[::-1])
            stack = []
    
    for row in range(len(warehouse)):
        if num in warehouse[row]:
            col = warehouse[row].index(num)
            break

    count = 0
    for r in range(row, len(warehouse)):
        if warehouse[r][col] != 0:
            count += 1

    return count