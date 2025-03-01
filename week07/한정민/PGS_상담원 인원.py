from collections import defaultdict
from collections import Counter
from itertools import combinations_with_replacement
import heapq

def solution(k, n, reqs):
    answer = float('inf')
    
    dic = defaultdict(list)
    for a, b, c in reqs:
        dic[c].append([a, b])
        
    arr = [i for i in range(1, k+1)]
    combi = list(combinations_with_replacement(arr, n))

    for c in combi:
        counter = Counter(c)
        # 각 유형별로 최소 1명씩은 배정되어야 함
        if len(counter) == k:
            wait = 0
            for key, values in dic.items():
                heap = []
                for i in range(len(values)):
                    start = values[i][0]
                    duration = values[i][1]
                    
                    # 멘토가 아직 남아있는 경우
                    if i < counter[key]:
                        heapq.heappush(heap, start + duration)
                    # 멘토가 모두 배정된 경우
                    else:
                        end = heapq.heappop(heap) # 가장 먼저 끝나는 상담
                        if end <= start:
                            heapq.heappush(heap, start + duration)
                        else:
                            wait += end - start
                            heapq.heappush(heap, end + duration)
                            
            answer = min(answer, wait)                        
                    
    return answer