# 컴퓨터 알고리즘 5주차 다익스트라 알고리즘으로 최단경로 찾기 - Greedy F조



## 1. 그리디 알고리즘이란?



### 1.1) 그리디 알고리즘이란?

**탐욕 알고리즘**이라고도 불리며  최적화 문제를 해결하는 알고리즘으로 가능한 해들 중에서 가장 좋은 해를 찾는 알고리즘이다.

그리디 알고리즘은 데이터 간의 관계를 고려하지 않고 수행과정에서 욕심내어 최소값 또는 최대값을 가진 데이터를 선택한다. 이러한 선택을 **근시안적인 선택**이라고 말하기도 한다. 

최종적으로 봤을 때 제일 좋은 선택이 아닐 수 있지만 **현재 상태**에서 가장 좋은 것을 선택



### 1.2) 사용되는 알고리즘 예시

* 거스름돈 문제(고객에게 주는 거스름돈 동전의 개수를 최소한으로 하는 문제)
* 최단경로 찾기

* 최소 신장 트리

* 허프만 코드

* 크루스칼, 프림 알고리즘

  

## 2. 다익스트라 알고리즘이란?



### 2.1)다익스트라 알고리즘이란?

**그리디 알고리즘**의 일종으로 전국 지역같이 각 꼭짓점에 간선들이 연결되어 나타날 수 있는 그래프에서 꼭짓점 간의 최단경로를 찾는 문제에서 쓰이는 대표적인 알고리즘이다.

Edsger Wybe Dijkstra 가 고안한 알고리즘으로, 그가 처음 고안한 알고리즘은 O(N^2)의 시간복잡도를 가졌다. 이후 우선순위 큐를 이용해 개선된 다익스트라 알고리즘이 나오며 O(NlogN)의 시간복잡도를 가지게 되었다.

### 2.2) 다익스트라 알고리즘 구현 방법

1. 출발점으로부터의 최단거리를 저장할 배열 D[]를 만들고, 노드들에는 매우 큰 값 INFI를 채워 넣는다. 경유하는 경로를 알기 위해 route[] 배열과, 방문처리용 배열 visited[] 도 만든다.
2. 현재 노드를 나타내는 변수 Current에 출발 노드의 번호를 저장한다.
3. 현재노드에 인접한 인접노드j 에 대해,d[Current] + P[Current] [ j ]와 d[ j ]의 값을 비교한다.
4. 만약 d[Current] + P[Current] [ j ]의 값이 더 작다면, 즉 더 짧은 경로라면, d[ j ]의 값을 이 값으로 갱신시킨다.
5. Current의 모든 인접노드들에 이 작업을 수행한다.
6. Current를 방문처리로 해주고 그 노드는 더이상 사용하지 않는다.
7. 방문처리가 안된 노드들 중(!visited), 출발점으로부터의 거리가 제일 짧은 노드 하나를 골라서 그 노드를 route에 저장한다.
8. 마지막 노드가 방문처리상태가 되거나, 혹은 더 이상 방문처리가 안 된 노드를 선택할 수 없을 때까지, 3~7의 과정을 계속 반복한다.



## 3. 설계과정 

asdfasdf







## 4. 다익스트라 알고리즘 코드 java



### 4.1) 초입

```java
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
```



1. 먼저 연결이 안 돼있는 노드까지의 거리를 무한대로 설정해야하지만 

int 형에서는 양수 약 21억 까지밖에 지원이 안되기도 해서 

지금 예제에서 쓰이지 않으면서 그래도 좀 큰 수를 임의로 INFI라는 이름의 변수에 집어 넣었다.



2. visited라는 배열을 만들어 그 노드를 방문한 적이 있는지 없는지 저장하게끔한다.



3. 우선 예제에서는 전국 각지의 10개지역밖에 없어서 우선 10개를 만들었다.



4. D배열은 시작 노드로부터 배열i번째 노드와의 거리를 저장하는 배열이다.



5. smallest 함수는 아직 방문하지 않은 노드중에 가장 짧은 거리를 반환하는 노드의 인덱스값을 반환하는 함수이다. 먼저 min 값이 초기값으로 제일 큰 값을 넣어주고, 인덱스는 0부터 시작한다. 그리고 for문으로 i는0부터 지역이 10개이니 0부터 10까지 i를 1개씩 증가시키면서 만약 아직 방문하지 않은 노드 중에서 현재 최소값min값 보다 더 작은 거리를 갖는 것이 있다면 최솟값min값에 그 값을 대입하고 인덱스에 그 때의 i값 (배열 내 위치)를 저장해줌으로써 가장 작은 것을 찾을 수 있게 계속 작은 값으로 갱신해준다.





### 4.2) 다익스트라를 실행하는 함수

```java
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
```

6. 다익스트라를 실행하는 함수이다. 최소비용(최소거리)이 담기는 배열 D에, 모든경로의 비용을 담는다. 또 cost 배열은 각각 지역으로 이동하면서 생기는 비용들을 저장한 배열이다. 
7. s는 시작점이다. 그러므로  시작점은 방문했습니다. 라고 처리해주는 방문처리코드이다.
8. 시작점 자기자신 부터 자기자신까지의 거리는 0이기 때문에 0으로 대입해준다.
9. n개의 노드 간선은 n-1개 이기 때문에 9번 돌린다.
10. smallest 함수(방문x노드 중 최소비용을 가지는 노드 인덱스 반환 함수)를 호출해서 가져온다.
11. 방문해준다.
12. 만약 아직 그 노드를 방문하지 않았다면
13. 현재 노드(current)까지의 최소비용과 그 노드를 거쳐서 그 노드에 인접한 노드에 가는 비용의 합이 현재 인접한 노드로 가는 최소비용보다 적다면 그 값으로 갱신해준다



### 4.3) main함수

```java
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
```

14. 지역번호가 1.서울 2.천안 3.원주 4.강릉 5.논산 6.대전 7.대구 8.포항 9.광주 10.부산
15. cost배열에서 0번째인 서울에서 출발하는 다익스트라 알고리즘 실행
16.  각 지역까지의 최소거리를 출력



## 2. 시간복잡도

위에서 구현한 다익스트라 알고리즘은 시간복잡도가 O(N^2) 이 나온다.

우선순위 큐를 이용하면 O(NlogN)의 복잡도를 만들 수 있다.