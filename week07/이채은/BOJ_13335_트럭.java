import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Queue<Integer> trains = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            trains.offer(Integer.parseInt(st.nextToken()));
        }

        Queue<Integer> currentTrains = new ArrayDeque<>();
        for(int i = 0; i < W; i++){
            currentTrains.offer(0);
        }

        int time = 0;
        int currentL = 0;
        while(!trains.isEmpty()){
            int exitTrain = currentTrains.poll();
            currentL -= exitTrain;

            if(currentL + trains.peek() <= L){
                int train = trains.poll();
                currentL += train;
                currentTrains.offer(train);
            } else{
                currentTrains.offer(0);
            }
            time++;
        }
        System.out.println(time+W);
    }
}