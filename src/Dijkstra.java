public class Dijkstra {

    //무한 정의
    final static int INFI = 100000000;

    // 노드가 방문한 노드인지 아닌지를 확인하는 배열값
    static boolean visited[] = {false,false,false,false,false,false,false,false,false,false};

    // 노드 경유 경로를 나타냄
    static int route[] = new int[10];

    // 노드와의 거리를 나타냄
    static int D[] = {INFI,INFI,INFI,INFI,INFI,INFI,INFI,INFI,INFI,INFI};


    // 가장 최소 거리를 가지는 노드를 반환
    // '방문하지 않았던 노드중에서 가장 짧은 거리' 를 가지는 노드
    static int smallest(){
        int min = INFI;
        int index = 0;
        for(int i = 0; i<10; i++){
            if(D[i] < min && !visited[i]){ //방문하지 않은 노드 중에서 현재 최솟값보다 더 작은 거리를 갖는 다면
                min = D[i]; //그 값으로 갱신한다.
                index = i; // 그 때의 위치를 저장!
            }
        }
        return index;

    }



    //다익스트라를 실행하는 함수
    static void dijkstra(int[][] cost, int s){ //s는 시작점!
        for(int i = 0; i<10;i++){
            D[i] = cost [s][i]; //최소비용(최소거리)이 담기는 배열 D에, 모든경로의 비용을 담는다.
        }
        visited[s] = true; //시작점은 방문했습니다 라고 방문처리를 해주는 코드임
        D[s] = 0; // 그 시작점에서부터 시작점까지는 자기자신이니까 0
        for(int i =0; i<9; i++){ //while루프 대신 for사용, 노드개수 n-1개까지 돌린다.
            int current = smallest();   //현재의 방문하지 않는 노드 중에서 최소비용을 가지는 노드의 인덱스를 가져와서
            visited[current] = true;      // 방문처리를 해준다.
            for(int j=0; j<10;j++){//모든 노드를 확인
                if(!visited[j]){  //만약 현재 노드를 방문하지 않았다면
                    if(D[current] + cost[current][j] < D[j]) {
                        D[j] = D[current] + cost[current][j];
                         //현재 노드(current)까지의 최소비용과 그 노드를 거쳐서 그 노드에 인접한 노드에
                        // 현재 인접한 노드로 가는 최소비용보다 적다면
                        route[j] = current; //갱신해준다. ppt 50번째 슬라이드 Line4
                    }
                }
            }
        }

    }


    public static void main(String[] args) {
        int cost[][] = {

                //지역번호 1.서울 2.천안 3.원주 4.강릉 5.논산 6.대전 7.대구 8.포항 9.광주 10.부산

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

        dijkstra(cost, 0);// cost배열에서 0번째 즉 서울에서부터 시작하는 다익스트라알고리즘 실행
        System.out.println("서울에서 출발해서 \n서울 천안 원주 강릉 논산 대전 대구 포항 광주 부산 까지의 거리는");
        for(int i = 0; i<10;i++){
            System.out.print(" " + D[i] + "\t"); // 각 지역까지의 최소거리를 출력
        }

    }
}

