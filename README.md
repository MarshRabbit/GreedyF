# GreedyF
# 테스트용 수정 4/9
# 과제 : 그리디 방식의 다익스트라 알고리즘으로 최단경로 찾기

## 1. 그리디 알고리즘
### 1.1 그리디 알고리즘이란?

**탐욕 알고리즘**이라고도 불리며  최적화 문제를 해결하는 알고리즘으로 가능한 해들 중에서 가장 좋은 해를 찾는 알고리즘이다.

그리디 알고리즘은 데이터 간의 관계를 고려하지 않고 수행과정에서 욕심내어 최소값 또는 최대값을 가진 데이터를 선택한다. 이러한 선택을 **근시안적인 선택**이라고 말하기도 한다. 

최종적으로 봤을 때 제일 좋은 선택이 아닐 수 있지만 **현재 상태**에서 가장 좋은 것을 선택한다

### 1.2 사용되는 알고리즘 예시

* 거스름돈 문제(고객에게 주는 거스름돈 동전의 개수를 최소한으로 하는 문제)
* 최단경로 찾기

* 최소 신장 트리

* 허프만 코드

* 크루스칼, 프림 알고리즘


## 2. 다익스트라 알고리즘이란?

다익스트라 알고리즘은 도로 교통망 같은 곳에서 나타날 수 있는 그래프에서 꼭짓점 간의 최단 경로를 찾는 알고리즘이다. 이 알고리즘은 컴퓨터 과학자 Edsger Wybe Dijkstra가 1956년에 고안했다. 

주어진 출발점에서부터 시작하여 출발점으로부터 최단 거리가 확정되지 않은 점들 중에서 출발점으로부터 가장 가까운 점을 추가하고, 그 점의 최단 거리를 확정하는 방식이다.


## 3. 설계과정

1. 배열 D를 INFI 무한에 가까운 수로 초기화 시킨다.
2. 최소거리 반환 함수와 노드 경유경로와, 최소거리를 저장하는 배열 생성
3. for 노드개수N-1 번
4. 출발점으로 부터 인접 노드까지 최소거리를 구한다.
5. 만약 d[Current] + P[Current] [ j ]의 값이 더 작다면, 즉 더 짧은 경로라면, d[ j ]의 값을 이 값으로 갱신

## 4. 자바 코드
```java
public class Dijkstra {	

    //1.무한 정의
    final static int INFI = 100000000;

    // 2.노드가 방문한 노드인지 아닌지를 확인하는 배열값
    static boolean visited[] = {false,false,false,false,false,false,false,false,false,false};

    // 3. 노드 경유 경로를 나타냄
    static int route[] = new int[10];

    // 4. 노드와의 거리를 나타냄
    static int D[] = {INFI,INFI,INFI,INFI,INFI,INFI,INFI,INFI,INFI,INFI};


    // 5. 가장 최소 거리를 가지는 노드를 반환
    static int smallest(){
        int min = INFI;
        int index = 0;
        for(int i = 0; i<10; i++){
            if(D[i] < min && !visited[i]){
                min = D[i];
                index = i;
            }
        }
        return index;

    }
//6. 다익스트라를 실행하는 함수
    static void dijkstra(int[][] cost, int s){ //s는 시작점!
        for(int i = 0; i<10;i++){
            D[i] = cost [s][i];
            
        }
        visited[s] = true; //7. 시작점 방문처리
        D[s] = 0; // 8.자기자신거리 0
        for(int i =0; i<9; i++){ //9.노드개수 n-1개까지 돌린다.
            int current = smallest();   //10.방문x최소비용노드 인덱스
            visited[current] = true;      // 11. 방문처리
            for(int j=0; j<10;j++){			//모든 노드를 확인
                if(!visited[j]){  		//12. 만약 현재 노드를 방문하지 않았다면
                    if(D[current] + cost[current][j] < D[j]) {
                        D[j] = D[current] + cost[current][j];
         //13. 현재 노드(current)까지의 최소비용과 그 노드를 거쳐서 그 노드에 인접한 노드에
         // 현재 인접한 노드로 가는 최소비용보다 적다면
                        route[j] = current; //갱신해준다. ppt 50번째 슬라이드 Line4
                    }
                }
            }
        }

    }
public static void main(String[] args) {
        int cost[][] = {

                //14. 거리비용 초기화
                {0, 12, 15, INFI, INFI, INFI, INFI, INFI, INFI, INFI}, //1.서울
                {12, 0, INFI, INFI, 4, 10,INFI,INFI, INFI,INFI}, //2.천안
                {15, INFI, 0, 21, INFI, INFI,7,INFI, INFI,INFI},//3.원주
                {INFI, INFI, 21, 0, INFI, INFI, INFI,25,INFI,INFI},//4.강릉
                {INFI, 4, INFI, INFI, 0, 3, INFI, INFI, 13, INFI},//5.논산
                {INFI, 10, INFI, INFI, 3, 0,10, INFI, INFI, INFI},//6.대전
                {INFI, 7, INFI, INFI, INFI, 10, 0, 19, INFI, 9},//7.대구
                {INFI, INFI, INFI, 25, INFI, INFI, 19, 0, INFI, 5},//8.포항
                {INFI, INFI, INFI, INFI, 13, INFI, INFI, INFI, 0, 15},//9.광주
                {INFI, INFI, INFI, INFI, INFI, INFI, 9, 5, 15, 0},//10.부산

        };

        dijkstra(cost, 0);// 15.서울부터 다익스트라 알고리즘 실행
        System.out.println("서울에서 출발해서 \n서울 천안 원주 강릉 논산 대전 대구 포항 광주 부산 까지의 거리는");
        for(int i = 0; i<10;i++){
            System.out.print(" " + D[i] + "\t"); //16. 각 지역까지의 최소거리를 출력
        }

    }
}
```

1. 먼저 연결이 안 돼있는 노드까지의 거리를 무한대로 설정해야하지만 int 형에서는 양수 약 21억 까지밖에 지원이 안되기도 해서 

   지금 예제에서 쓰이지 않으면서 그래도 좀 큰 수를 임의로 INFI라는 이름의 변수에 집어 넣었다.

2. visited라는 배열을 만들어 그 노드를 방문한 적이 있는지 없는지 저장하게끔한다.

3. 우선 예제에서는 전국 각지의 10개지역밖에 없어서 우선 10개를 만들었다.

4. D배열은 시작 노드로부터 배열i번째 노드와의 거리를 저장하는 배열이다.

5. smallest 함수는 아직 방문하지 않은 노드중에 가장 짧은 거리를 반환하는 노드의 인덱스값을 반환하는 함수이다. 먼저 min 값이 초기값으로 제일 큰 값을 넣어주고, 인덱스는 0부터 시작한다. 그리고 for문으로 i는0부터 지역이 10개이니 0부터 10까지 i를 1개씩 증가시키면서 만약 아직 방문하지 않은 노드 중에서 현재 최소값min값 보다 더 작은 거리를 갖는 것이 있다면 최솟값min값에 그 값을 대입하고 인덱스에 그 때의 i값 (배열 내 위치)를 저장해줌으로써 가장 작은 것을 찾을 수 있게 계속 작은 값으로 갱신해준다.

6. 다익스트라를 실행하는 함수이다. 최소비용(최소거리)이 담기는 배열 D에, 모든경로의 비용을 담는다. 또 cost 배열은 각각 지역으로 이동하면서 생기는 비용들을 저장한 배열이다. 

7. s는 시작점이다. 그러므로  시작점은 방문했습니다. 라고 처리해주는 방문처리코드이다.

8. 시작점 자기자신 부터 자기자신까지의 거리는 0이기 때문에 0으로 대입해준다.

9. n개의 노드 간선은 n-1개 이기 때문에 9번 돌린다.

10. smallest 함수(방문x노드 중 최소비용을 가지는 노드 인덱스 반환 함수)를 호출해서 가져온다.

11. 방문해준다.

12. 만약 아직 그 노드를 방문하지 않았다면

13. 현재 노드(current)까지의 최소비용과 그 노드를 거쳐서 그 노드에 인접한 노드에 가는 비용의 합이 현재 인접한 노드로 가는 최소비용보다 적다면 그 값으로 갱신해준다

14. 지역번호가 1.서울 2.천안 3.원주 4.강릉 5.논산 6.대전 7.대구 8.포항 9.광주 10.부산

15. cost배열에서 0번째인 서울에서 출발하는 다익스트라 알고리즘 실행

16. 각 지역까지의 최소거리를 출력


## 5. 문제점
이 기본적인 방식의 알고리즘을 사용하면 시간복잡도가 O(N<sup>2</sup>)이므로 노드의 개수가 많아지면 연산횟수가 커지는 단점이 존재한다.

하지만 우선순위 큐를 사용한다면 출발 노드와 가장 짧은 거리를 가진 노드를 먼저 꺼내게 되므로 일반적으로 시간복잡도를 줄일 수 있다.

## 6. 우선순위 큐 사용 다익스트라 
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

## 7. 시간복잡도 비교
일반적인 배열을 사용한 방식은 모든 노드(V)에 대해 최소거리를 찾아야하기 때문에 노드의 제곱인 O(V<sup>2</sup>)가 걸린다.

여기서 우선순위 큐를 사용한다면 모든 간선에 대해서(O(E)) 큐를 업데이트(O(logV)) 하므로 O(ElogV)가 나온다.

일반적으로 O(V<sup>2</sup>)보다 O(ElogV)가 더 빠르지만 만일 최단거리를 찾고자 하는 그래프가 완전 그래프일 경우엔 E = V<sup>2</sup>이 되므로 일반적인 방식이 더 빨라 사용에 유의가 필요하다.
