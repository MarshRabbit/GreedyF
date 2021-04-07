# GreedyF
# 과제 : 그리디 방식의 다익스트라 알고리즘으로 최단경로 찾기

## 1. 그리디 알고리즘이란?

**탐욕 알고리즘**이라고도 불리며  최적화 문제를 해결하는 알고리즘으로 가능한 해들 중에서 가장 좋은 해를 찾는 알고리즘이다.

그리디 알고리즘은 데이터 간의 관계를 고려하지 않고 수행과정에서 욕심내어 최소값 또는 최대값을 가진 데이터를 선택한다. 이러한 선택을 **근시안적인 선택**이라고 말하기도 한다. 

최종적으로 봤을 때 제일 좋은 선택이 아닐 수 있지만 **현재 상태**에서 가장 좋은 것을 선택


## 2. 다익스트라 알고리즘이란?


## 3. 설계과정


## 4. 자바 코드
```java
import java.util.*;

public class greedy {
    private void dijkstra (int[][] A, int[] temp, int[] dist) {
        init(A, temp, dist);

        int x, y;
        for (int i = 0; i < A.length-1; i++) {
            x = select(temp, dist);
            temp[x] = 1;
            for (y = 0; y < A.length; y++) {
                if (temp[y] == 0 && dist[y] > dist[x] + A[x][y])
                    dist[y] = dist[x] + A[x][y];
            }
        }

    }

    private void init(int[][] A, int[] temp, int[] dist) {
        temp[0] = 1;
        for (int i = 1; i < temp.length; i++) {
            temp[i] = 0;
        }
        for (int i = 0; i < dist.length; i++) {
            dist[i] = A[0][i];
        }
    }

    private int select(int[] temp, int[] dist) {
        int min = 9999999;
        int p = -1;
        for (int i = 0; i < temp.length; i++) {
            if (dist[i] < min && temp[i]==0) {
                min = dist[i];
                p = i;
            }
        }
        return p;
    }


    public static void main(String[] args) {
        int[][] graph = new int [6][6];
        graph[0] = new int [] {0, 4, 5, 7, 9999999, 9999999};
        graph[1] = new int [] {4, 0, 9999999, 9999999, 9, 9999999};
        graph[2] = new int [] {5, 9999999, 9999999, 9999999, 9999999, 1};
        graph[3] = new int [] {7, 9999999, 9999999, 0, 2, 3};
        graph[4] = new int [] {9999999, 9, 9999999, 2, 0, 8};
        graph[5] = new int [] {9999999, 9999999, 1, 3, 8, 0};

        int[] t = new int[graph.length]; // 방문한 꼭짓점을 저장할 배열
        int[] d = new int[graph.length]; // 시작정점에서부터 다른정점까지의 길이를 저장할 배열

        greedy fast = new greedy();
        fast.dijkstra(graph, t, d);
        for (int i=0; i<graph.length; i++){
            System.out.print(t[i]+" ");
        }
        
        System.out.println("");
        
        for (int i=0; i<graph.length; i++){ // 출발점에서부터 꼭짓점별 까지 가중치 값 
            System.out.print(d[i]+" ");
        }
    }
}

```

## 5. 우선순위 큐 사용
```java
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
    public int compareTo(Element o){
        return dist - o.dist;
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

            for (int i=0; i<visited.length; i++) // 각 꼭짓점까지 방문했는지 확인
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
        graph[2] = new int [] {5, INF, INF, INF, INF, 1};
        graph[3] = new int [] {7, INF, INF, 0, 2, 3};
        graph[4] = new int [] {INF, 9, INF, 2, 0, 8};
        graph[5] = new int [] {INF, INF, 1, 3, 8, 0};

        int[] t = new int[graph.length]; // 방문한 꼭짓점을 저장할 배열
        int[] d = new int[graph.length]; // 시작정점에서부터 다른정점까지의 길이를 저장할 배열

        greedy fast = new greedy();
        fast.dijkstra(graph, t, d);

        System.out.println("");
        for (int i=0; i<graph.length; i++) // 출발점에서부터 각 꼭짓점들 까지 가중치 값
            System.out.print(d[i]+" ");
    }
}


```
