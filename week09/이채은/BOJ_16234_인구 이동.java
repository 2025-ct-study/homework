import java.io.*;
import java.util.*;

public class Main {

    public static class Point{
        int y,x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static class Union{
        int totalPeople;
        ArrayList<Point> countries;
        public Union(int totalPeople, ArrayList<Point> countries){
            this.totalPeople = totalPeople;
            this.countries = countries;
        }
    }

    public static int N, L, R;
    public static int[][] countries;
    public static int[] dy = {1, -1, 0, 0};
    public static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        countries = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                countries[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while(true){
            // 연합을 탐색한다. (bfs)
            ArrayList<Union> unions = getUnions();

            // 연합이 없으면 탈출
            if(unions.isEmpty()) break;

            // 인구 이동을 한다.
            for(Union union : unions){
                ArrayList<Point> unionContries = union.countries;
                int movedPeople = union.totalPeople / unionContries.size();
                for(Point unionContry: unionContries){
                    countries[unionContry.y][unionContry.x] = movedPeople;
                }
            }
            cnt++;
        }

        System.out.println(cnt);
    }

    public static ArrayList<Union> getUnions(){
        ArrayList<Union> unions = new ArrayList<>();

        boolean[][] visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]){
                    int people = 0;
                    ArrayList<Point> list = new ArrayList<>();

                    //bfs
                    Queue<Point> q = new ArrayDeque<>();
                    q.offer(new Point(i,j));
                    visited[i][j] = true;
                    while(!q.isEmpty()){
                        Point point = q.poll();
                        list.add(point);
                        people += countries[point.y][point.x];

                        for(int d = 0; d < 4; d++){
                            Point nextPoint = new Point(point.y + dy[d], point.x + dx[d]);
                            if(isValid(nextPoint) && !visited[nextPoint.y][nextPoint.x] && isOpened(point,nextPoint)){
                                q.offer(nextPoint);
                                visited[nextPoint.y][nextPoint.x] = true;
                            }
                        }
                    }

                    if(list.size() > 1){
                        unions.add(new Union(people, list));
                    }
                }
            }
        }

        return unions;
    }

    public static boolean isValid(Point point){
        if(point.y >= 0 && point.x >= 0 && point.y < N && point.x < N) return true;
        return false;
    }

    public static boolean isOpened(Point pointA, Point pointB){
        int diff = Math.abs(countries[pointA.y][pointA.x]-countries[pointB.y][pointB.x]);
        if(diff >= L && diff <= R) return true;
        return false;
    }
}