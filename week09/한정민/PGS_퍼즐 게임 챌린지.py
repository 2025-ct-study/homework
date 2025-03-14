def solution(diffs, times, limit):
    
    def check(level):
        total_time = times[0] 
        for i in range(1, len(diffs)):
            if diffs[i] <= level:
                total_time += times[i]
            else:
                mistakes = diffs[i] - level
                total_time += mistakes * (times[i] + times[i - 1]) + times[i]
            if total_time > limit:
                return False
        return True

    left, right = 1, max(diffs)
    answer = right

    while left <= right:
        mid = (left + right) // 2
        if check(mid):
            answer = mid
            right = mid - 1
        else:
            left = mid + 1

    return answer
