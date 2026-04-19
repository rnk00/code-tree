import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        
        int dx[] = {0,1,0,-1};
        int dy[] = {1,0,-1,0};

        int answer = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){  // 중심점

                for(int k=0; k<=n; k++){  // 마름모 크기
                    int gold = 0;

                    for(int x=0; x<n; x++){
                        for(int y=0; y<n; y++){
                            if(Math.abs(x - i) + Math.abs(y - j) <= k){
                                gold += grid[x][y];
                            }
                        }
                    }

                    int cost = k*k + (k+1)*(k+1);

                    if(gold * m >= cost){
                        answer = Math.max(answer, gold);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}