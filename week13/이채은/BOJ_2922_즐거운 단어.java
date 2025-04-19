import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<Integer> removed;
    public static int removedNum;
    public static long totalCnt = 0;

    public static final int vowels = 5;
    public static final int consonants = 21;
    public static Set<Character> vowelSet
            = new HashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U'));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(br.readLine());
        boolean containsL = sb.toString().contains("L");

        // 모음 -> 1, 자음 -> 0 바꿔줌
        removed = new ArrayList<>();
        for(int i = 0; i < sb.length(); i++){
            if(sb.charAt(i) == '_') removed.add(i);
            else if(vowelSet.contains(sb.charAt(i))) sb.setCharAt(i,'1');
            else sb.setCharAt(i,'0');
        }
        removedNum = removed.size();

        bt(sb, containsL, 0, 1);
        System.out.println(totalCnt);
    }

    public static void bt(StringBuilder sb, boolean containsL, int idx, long cnt){
        if(idx == removedNum){
            if(containsL) totalCnt += cnt;
            return;
        }

        if(canVowel(sb,idx)) bt(sb, containsL, idx+1, cnt * vowels);
        sb.setCharAt(removed.get(idx),'_');

        if(canConsonant(sb,idx)){
            bt(sb, true, idx+1, cnt); // L
            bt(sb, containsL, idx+1, cnt * (consonants-1)); // L 제외 자음
        }
        sb.setCharAt(removed.get(idx),'_');
    }

    public static boolean canVowel(StringBuilder sb, int idx){
        sb.setCharAt(removed.get(idx),'1');
        return sb.toString().contains("111") ? false : true;
    }

    public static boolean canConsonant(StringBuilder sb, int idx){
        sb.setCharAt(removed.get(idx),'0');
        return sb.toString().contains("000") ? false : true;
    }
}