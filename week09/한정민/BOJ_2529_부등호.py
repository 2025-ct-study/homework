k = int(input())
brackets = list(input().split())
visited = [False] * 10

max_value = "0" * (k + 1)
min_value = "9" * (k + 1)

def check(op, i, j):
    if op == '<':
        return i < j
    if op == '>':
        return i > j
    return False

def dfs(depth, num):
    global max_value, min_value

    if depth == k + 1:
        num_str = ''.join(map(str, num))
        max_value = max(max_value, num_str)
        min_value = min(min_value, num_str)
        return 
    
    for i in range(10):
        if not visited[i]:
            if depth == 0 or check(brackets[depth - 1], num[-1], i):
                visited[i] = True
                dfs(depth + 1, num + [i])
                visited[i] = False

dfs(0, [])
print(max_value)
print(min_value)