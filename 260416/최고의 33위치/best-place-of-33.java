import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max_coin = 0;
        for(int i=0; i<=N-3; i++){
            for(int j=0; j<=N-3; j++){
                int cnt = 0;
                for(int i2=0; i2<3; i2++){
                    for(int j2=0; j2<3; j2++){
                        if(arr[i+i2][j+j2]==1) cnt++;
                    }
                }
                max_coin = Math.max(max_coin, cnt);
            }
        }

        System.out.println(max_coin);
    }
}