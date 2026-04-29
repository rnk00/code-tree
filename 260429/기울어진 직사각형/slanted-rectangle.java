import java.io.*;
import java.util.*;

public class Main {
    static int dx[] = {-1,-1,1,1};
    static int dy[] = {1,-1,-1,1};

	static int N;
	static int arr[][];
    
	public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine().trim());
    	
    	arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    	
		int ans = 0;
    	
    	for(int i=2; i<N; i++) {
    		for(int j=1; j<N-1; j++) {
    			for(int w=1; w<N; w++) {
    				for(int h=1; h<N; h++) {
    					ans = Math.max(ans, getScore(i,j,w,h));
    				}
    			}
    		}
    	}

		System.out.println(ans);
    }

	public static int getScore(int x, int y, int w, int h){
		int[] dir = {w, h, w, h};
		int sum = 0;

		for(int d=0; d<4; d++){
			for(int len=0; len<dir[d]; len++){
				x += dx[d];
				y += dy[d];

				if(x<0 || x>=N || y<0 || y>=N) return 0;
				sum += arr[x][y];
			}
		}
		return sum;
	}
}

