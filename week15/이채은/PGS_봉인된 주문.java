import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        StringBuilder sb = new StringBuilder(); // a:97  z:122

        // bans 문자열 -> 순번으로 변환
        long[] banNums = new long[bans.length];
        for(int i = 0; i < bans.length; i++){
            String curBan = bans[i];
            int curBanLen = bans[i].length();
            for(int j = 0; j < curBanLen; j++){
                banNums[i] += (curBan.charAt(j) - 'a' + 1) * Math.pow(26, curBanLen-1-j);
            }
        }

        // n보다 앞에 있는(작거나 같은) ban이 있으면 n++
        Arrays.sort(banNums);
        for(int i = 0; i < banNums.length; i++){
            if(banNums[i] <= n) n++;
            else break;
        }

        // n번째 문자열 반환 (뒤집기 전)
        long divider = n;
        while (divider > 0) {
            long remainder = divider % 26;

            if (remainder == 0) {  // 26으로 나누어떨어지는 경우는 'z'
                sb.append('z');
                divider = divider / 26 - 1; // 몫에서 -1 해줘야 함
            } else {
                sb.append((char)(remainder + 96)); // 1→a(97), 2→b ...
                divider /= 26;
            }
        }

        // 문자열 뒤집기
        return sb.reverse().toString();
    }
}