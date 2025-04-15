def solution(n, s, a, b, fares):
    answer = 0
    
    INF = int(1e9)
    graph = [[INF] * (n+1) for _ in range(n+1)]
    for i in range(1, n+1):
        for j in range(1, n+1):
            if i == j:
                graph[i][j] = 0
    
    for fare in fares:
        graph[fare[0]][fare[1]] = fare[2]
        graph[fare[1]][fare[0]] = fare[2]
    
    for k in range(1, n+1):
        for i in range(1, n+1):
            for j in range(1, n+1):
                graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])
    
    answer = graph[s][a] + graph[s][b]
    for k in range(1, n+1):
        answer = min(answer, graph[s][k] + graph[k][a] + graph[k][b])
        
    return answer