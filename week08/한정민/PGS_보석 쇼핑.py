def solution(gems):

    n = len(gems)
    answer = [0, n - 1]
    kind = len(set(gems))
    dic = {gems[0] : 1,}
    start, end = 0, 0 # 투포인터터
    
    while start < n and end < n:
        # 종류 부족하면 end 증가 & dic 개수 증가
        if len(dic) < kind:
            end += 1
            if end == n:
                break
            dic[gems[end]] = dic.get(gems[end], 0) + 1
        
        # 모든 종류 포함하고 있으면 answer 비교하여 답 갱신 & start 증가 & dic 개수 감소
        else:
            if (end - start + 1) < (answer[1] - answer[0] + 1):
                answer = [start, end]
                
            if dic[gems[start]] == 1:
                del dic[gems[start]]
            else:
                dic[gems[start]] -= 1
                
            start += 1
    
    answer[0] += 1
    answer[1] += 1
    return answer