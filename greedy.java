package Lab01;
import java.util.*;

class Element implements Comparable<Element>{
    int idx; // 꼭짓점
    int dist; // 거리

    Element(int idx, int distance){
        this.idx = idx;
        this.dist = distance;
    }

    public int getIdx(){
        return idx;
    }

    public int getDist(){
        return dist;
    }

    @Override
    public int compareTo(Element e){
        return dist - e.dist;
    }
}

public class greedy {

    static final int INF = 99999999; // 직접 방문 불가

    private void dijkstra (int[][] A, int[] visited, int[] dist) {
        PriorityQueue <Element> queue = new <Element> PriorityQueue(); // 우선순위 큐 생성
        init(visited, dist);
        queue.add(new Element(0, 0)); // 출발점 0을 우선순위큐에 삽입

        while (!queue.isEmpty()) {
            int v = queue.peek().getIdx();  // 꼭짓점
            int w = queue.peek().getDist(); // 가중치
            queue.poll(); // 큐의 첫번째 값 반환

            if (w > dist[v]) //최단거리보다 긴 경우이므로 패스
                continue;

            for (int i = 1; i < 6; i++) { // 출발점이 0이므로 1부터
                if (A[v][i] != 0 && dist[i] > dist[v] + A[v][i]) { // 자기 자신한테 향하는 경우 제외하고, 거처서 가는게 더 빠를경우
                    dist[i] = dist[v] + A[v][i];  // 최단거리값 삽입
                    queue.add(new Element(i, dist[i]));  // 최단거리인 꼭짓점을 우선순위큐에 삽입
                }
            }

            visited[v] = 1; // 방문한 꼭짓점을 체크

            for (int i = 0; i < visited.length; i++) // 각 꼭짓점까지 방문했는지 확인
                System.out.print(visited[i]+" ");
            System.out.println("");
        }

    }

    private void init(int[] visited, int[] dist) {
        visited[0] = 1; // 시작점
        dist[0] = 0;
        for (int i = 1; i < visited.length; i++)
            visited[i] = 0;
        for (int i = 1; i < dist.length; i++)
            dist[i] = INF;
    }


    public static void main(String[] args) {
        int[][] graph = new int [6][6];
        graph[0] = new int [] {0, 4, 5, 7, INF, INF};
        graph[1] = new int [] {4, 0, INF, INF, 9, INF};
        graph[2] = new int [] {5, INF, 0, INF, INF, 1};
        graph[3] = new int [] {7, INF, INF, 0, 2, 3};
        graph[4] = new int [] {INF, 9, INF, 2, 0, 8};
        graph[5] = new int [] {INF, INF, 1, 3, 8, 0};

        int[] t = new int[graph.length]; // 방문한 꼭짓점을 저장할 배열
        int[] d = new int[graph.length]; // 시작정점에서부터 다른정점까지의 길이를 저장할 배열

        greedy fast = new greedy();
        fast.dijkstra(graph, t, d);

        System.out.println("");
        for (int i = 0; i < graph.length; i++) // 출발점에서부터 각 꼭짓점들 까지 가중치 값
            System.out.print(d[i]+" ");
    }
}
