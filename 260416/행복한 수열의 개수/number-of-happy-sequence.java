/*
행복한 수열: 연속하여 M개 이상의 동일한 원소가 나오는 순간이 존재하는 수열
가로 N열, 세로 N열 중 행복한 수열의 개수는?
*/

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int happyCnt = 0;

        int[][] arr = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(M==1) happyCnt = 2*N;
        else {
            for(int i=0; i<N; i++){
                int cnt = 0;
                int prev = arr[i][0];
                for(int j=1; j<N; j++){
                    if(arr[i][j]==prev){
                        cnt++;
                        if(cnt==M){
                            happyCnt++;
                            break;
                        }
                    }
                    else{
                        cnt=1;
                        prev = arr[i][j];
                    }
                }
            }

            for(int j=0; j<N; j++){
                int cnt = 1;
                int prev = arr[0][j];
                for(int i=1; i<N; i++){
                    if(arr[i][j]==prev){
                        cnt++;
                        if(cnt==M){
                            happyCnt++;
                            break;
                        }
                    }
                    else{
                        cnt=1;
                        prev = arr[i][j];
                    }
                }
            }
        }

        System.out.println(happyCnt);
    }
}