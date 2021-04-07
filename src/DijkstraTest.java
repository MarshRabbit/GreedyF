import java.util.PriorityQueue;

class Element implements Comparable<Element> {
    int index;
    int distance;


    public Element(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }


    @Override
    public int compareTo(Element e) {
        return this.distance - e.distance;
    }


}

public class DijkstraTest {
    static boolean[] visited;
    static int[] D;
    static int[][] cost;
    static int E = 14, V = 10;
    static int INFI = 100000;



    public static void DijkstaMethod(int start) {
        PriorityQueue<Element> prq = new PriorityQueue<>();
        D[start] = 0;
        prq.offer(new Element(start, D[start]));

        while(!prq.isEmpty()) {
            int cost = prq.peek().distance;
            int current = prq.peek().index;
            prq.poll();

            if (cost > D[current]) {   // 현재 배열에 있는 값(최단 거리)보다 cost가 더 크면
                continue;
            }

            for (int i = 1; i <= V; ++i) {
                if (DijkstraTest.cost[current][i] != 0 && D[i] > (D[current] + DijkstraTest.cost[current][i])) {
                    D[i] = D[current] + DijkstraTest.cost[current][i];
                    prq.offer(new Element(i, D[i]));
                }
            }

        }

    }

    public static void main(String[] args) {
        visited = new boolean[V + 1];
        D = new int[V + 1];
        cost = new int[V + 1][V + 1];

        for (int i = 1; i <= V; ++i) {
            D[i] = INFI;
        }

        cost[1][2] = 12;
        cost[1][3] = 15;
        cost[2][5] = 4;
        cost[2][6] = 10;
        cost[3][4] = 21;
        cost[3][7] = 7;
        cost[4][8] = 25;
        cost[5][6] = 3;
        cost[5][9] = 13;
        cost[6][7] = 10;
        cost[7][8] = 19;
        cost[7][10] = 9;
        cost[8][10] = 5;
        cost[9][10] = 15;



        //지역번호 1.서울 2.천안 3.원주 4.강릉 5.논산 6.대전 7.대구 8.포항 9.광주 10.부산

        DijkstaMethod(1);
        System.out.println("서울에서 출발해서 \n서울 천안 원주 강릉 논산 대전 대구 포항 광주 부산 까지의 거리는");
        for (int i = 1; i <= V; ++i) {   // dist 배열의 중간 결과 보여주기
            System.out.print(" " + D[i] + "\t");
        }

    }
}