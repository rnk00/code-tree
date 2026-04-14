import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
    int to;          // 목적지 노드 번호
    int distance;    // 목적지 노드까지의 거리

    public Node(int to, int distance){
        this.to = to;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node other){
        return Integer.compare(this.distance, other.distance);
    }
}

public class Main{
    static final int INF = Integer.MAX_VALUE;
    static List<List<Node>> graph = new ArrayList<>();
    static int[] dist;  // 시작점으로부터의 최단거리

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 정점 개수
        int M = Integer.parseInt(st.nextToken());  // 간선 개수

        dist = new int[N+1];
        Arrays.fill(dist, INF);  // 초기 최단 거리는 모두 무한대

        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());  // 출발 노드
            int v = Integer.parseInt(st.nextToken());  // 도착 노드
            int w = Integer.parseInt(st.nextToken());  // 가중치
            graph.get(u).add(new Node(v, w));  // u->v 간선 추가
        }

        dijkstra(1);  // 1번 정점에서 시작

        StringBuilder sb = new StringBuilder();
        for(int i=2; i<=N; i++){
            if(dist[i] == INF){
                sb.append("-1\n");  // 경로가 존재하지 않음
            }
            else{
                sb.append(dist[i]).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;  // 자기자신까지의 거리
        pq.offer(new Node(start, 0));  // pq에 시작점 추가

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int now = cur.to;  // 현재 방문 중인 노드 번호
            int d = cur.distance;  // 현재까지의 계산된 거리

            // 기존에 저장된 최단 거리가 현재 꺼낸 거리가 작으면 이미 처리된 것
            if(dist[now] < d) continue;

            for(Node neighbor: graph.get(now)){
                int cost = dist[now] + neighbor.distance;
                // 현재 노드를 거쳐 인접 노드로 갈 때와 기존 최단 거리를 비교
                if(dist[neighbor.to] > cost){
                    dist[neighbor.to] = cost;
                    pq.offer(new Node(neighbor.to, cost));
                }
            }
        }
    }
}