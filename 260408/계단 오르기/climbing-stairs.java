import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] dp = new int[1005];
        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
        for(int i=4; i<=1000; i++){
            dp[i] = dp[i-2] + dp[i-3];
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        System.out.println(dp[N] % 10007);
    }
}