import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int arr[][] = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int dx[][] = {{0,0,1},{0,1,1},{0,1,1},{1,0,0},{0,1,2},{0,0,0}};
        int dy[][] = {{0,1,1},{1,1,0},{0,0,1},{0,0,1},{0,0,0},{0,1,2}};

        int max = 0;
        // 시작점 찾기
        for(int i=0; i<N-1; i++){
            for(int j=0; j<M-1; j++){
                // 모양대로 더하기
                for(int d=0; d<6; d++){
                	int sum = 0;
                	boolean can = true;
                    for(int k=0; k<3; k++){
                        int nx = i+dx[d][k];
                        int ny = j+dy[d][k];

                        if(nx<0 || nx>=N || ny<0 || ny>=M) {
                            can = false;
                            break;
                        }

                        sum += arr[nx][ny];
                    }
                    if(can){
                    	max = Math.max(max, sum);
                    }
                }
            }
        }

        System.out.println(max);
    }
}