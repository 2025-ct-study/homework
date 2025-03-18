def solution(players, m, k):
    answer = 0
    servers = [0] * 24
    
    for i in range(24):
        remaining_players = players[i] - (servers[i] * m)
        
        if remaining_players >= m:
            additional_servers = remaining_players // m
            end = min(i + k, 24)
            for j in range(i, end):
                servers[j] += additional_servers
            answer += additional_servers
            
    return answer