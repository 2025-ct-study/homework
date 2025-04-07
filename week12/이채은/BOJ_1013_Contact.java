import java.io.*;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("((100+1+)|01)+");
        for(int i = 0; i < N; i++){
            // 정규 표현식으로 패턴 비교
            String signal = br.readLine();
            if(pattern.matcher(signal).matches()) sb.append("YES");
            else sb.append("NO");

            sb.append('\n');
        }

        System.out.println(sb);
    }
}