def solution(users, emoticons):
    answer = [0, 0]
    data = [10, 20, 30, 40]
    discount = []
    
    # 할인율 조합 구하기
    def dfs(temp, d):
        if d == len(temp):
            discount.append(temp[:])
            return
        else:
            for i in data:
                temp[d] += i
                dfs(temp, d + 1)
                temp[d] -= i # 백트래킹
                
    dfs([0] * len(emoticons), 0)
    
    for disc in discount:
        count = 0 # 이모티콘 플러스 가입자 수
        total = 0 # 이모티콘 매출액
        for user in users:
            pay = 0
            for i in range(len(disc)):
                if disc[i] >= user[0]:
                    pay += emoticons[i] * ((100 - disc[i]) / 100)
                if pay >= user[1]:
                    break
            if pay >= user[1]:
                pay = 0
                count += 1
            else:
                total += pay
                
        if count >= answer[0]:
            if count == answer[0]:
                answer[1] = max(total, answer[1])
            else:
                answer[1] = total
            answer[0] = count
            
    return answer