import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 상 좌 하 우
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    // step2 이동 우선순위: 좌 하 우 상
    static int[] step2Priority = {1, 2, 3, 0};

    static int[][] sea;
    static boolean[][] visit;
    static int N, r, c, d, cnt;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        if (d == 1) d = 0;
        else if (d == 2) d = 2;
        else if (d == 3) d = 1;
        else d = 3;

        sea = new int[N + 1][N + 1];
        visit = new boolean[N + 1][N + 1];
        cnt = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
                if (sea[i][j] == 0) cnt++;
            }
        }

        // 시작 위치 처리
        visit[r][c] = true;
        cnt--;
        sb.append(r).append(' ').append(c).append('\n');

        while (cnt > 0) {
            int preCnt = cnt;
            int d2 = d;

            // 1단계
            step1(d2);								// 직진
            if (cnt == preCnt) step1((d2 + 1) % 4);	// 좌회전
            if (cnt == preCnt) step1((d2 + 3) % 4);	// 우회전
            if (cnt == preCnt) step1((d2 + 2) % 4);	// 유턴

            // 2단계
            if (cnt == preCnt) {
                d = d2;
                if (!step2()) break;
            }
        }

        System.out.print(sb);
    }

    static void step1(int dir) {
        int nr = r + dr[dir];
        int nc = c + dc[dir];

        if (nr < 1 || nr > N || nc < 1 || nc > N) return;
        if (sea[nr][nc] == 1 || visit[nr][nc]) return;

        visit[nr][nc] = true;
        r = nr;
        c = nc;
        d = dir;
        cnt--;
        sb.append(r).append(' ').append(c).append('\n');
    }

    static boolean step2() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] v = new boolean[N + 1][N + 1];  // step2용 visit

        // 시작 위치 처리
        q.add(new int[]{r, c});
        v[r][c] = true;

        // 같은 거리의 바다들
        List<int[]> targets = new ArrayList<>();
        boolean found = false;

        // BFS
        while (!q.isEmpty() && !found) {
            int size = q.size();
            List<int[]> temp = new ArrayList<>();

            for (int s = 0; s < size; s++) {
                int[] cur = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = cur[0] + dr[i];
                    int nc = cur[1] + dc[i];
                    
                    if (nr < 1 || nr > N || nc < 1 || nc > N) continue;
                    if (sea[nr][nc] == 1 || v[nr][nc]) continue;
                    
                    v[nr][nc] = true;
                    if (!visit[nr][nc]) {
                        temp.add(new int[]{nr, nc});
                        found = true;
                    }
                    q.add(new int[]{nr, nc});
                }
            }
            if (found) {
                targets = temp;
                break;
            }
        }

        if (targets.isEmpty()) return false;

        targets.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int tr = targets.get(0)[0];
        int tc = targets.get(0)[1];

        // target에서 역BFS
        int[][] dist = new int[N + 1][N + 1];
        for (int[] row : dist) Arrays.fill(row, -1);
        Queue<int[]> bfsQ = new LinkedList<>();
        dist[tr][tc] = 0;
        bfsQ.add(new int[]{tr, tc});

        while (!bfsQ.isEmpty()) {
            int[] cur = bfsQ.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (nr < 1 || nr > N || nc < 1 || nc > N) continue;
                if (sea[nr][nc] == 1 || dist[nr][nc] != -1) continue;
                dist[nr][nc] = dist[cur[0]][cur[1]] + 1;
                bfsQ.add(new int[]{nr, nc});
            }
        }

        // 경로 이동: 중간 경유는 방문 처리만, 목적지만 출력
        while (!(r == tr && c == tc)) {
            int curDist = dist[r][c];
            for (int p : step2Priority) {
                int nr = r + dr[p];
                int nc = c + dc[p];
                if (nr < 1 || nr > N || nc < 1 || nc > N) continue;
                if (sea[nr][nc] == 1 || dist[nr][nc] == -1) continue;
                if (dist[nr][nc] == curDist - 1) {
                    r = nr;
                    c = nc;
                    d = p;
                    if (!visit[r][c]) {
                        visit[r][c] = true;
                        cnt--;
                    }
                    break;
                }
            }
        }
        sb.append(r).append(' ').append(c).append('\n');
        return true;
    }
}