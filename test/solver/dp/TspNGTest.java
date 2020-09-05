/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver.dp;

import debug.Te;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author masao
 */
public class TspNGTest {
    int vertices = 5;
    int[][] dp = new int[1<<vertices][vertices];
    int[][] graph = new int[vertices][vertices];
    Tsp tsp;
    public TspNGTest() {
        for (int i=0;i<vertices;i++) for(int j=0;j<vertices;j++) graph[i][j]=10000;
        int[][] tested = {
            {0,1,3},
            {0,3,4},
            {1,2,5},
            {2,0,4},
            {2,3,5},
            {3,4,3},
            {4,0,7},
            {4,1,6}
        };
        for (int i=0;i<tested.length;i++) graph[tested[i][0]][tested[i][1]]=tested[i][2];
        tsp=new Tsp(5,graph);
    }
    
    public int tsp() {
        for (int i=0; i<(1<<vertices);i++) for (int j=0; j<vertices;j++) dp[i][j]=10000;
        dp[0][0]=0;
        for (int S=1; S<(1<<vertices);S++)
            for(int from=0; from<vertices; from++) for(int to=0;to<vertices;to++) if ((S&(1<<from))!=0)
                dp[S][to]=Integer.min(dp[S][to],dp[S-(1<<from)][from]+graph[from][to]);
        return dp[(1<<vertices)-1][0];
    }
    
    @Test
    public void testTsp() {
        System.out.println(test.TestUtils.methodName(0));
        int result = tsp.solve();
        int expected = 22;
        System.out.println("result  : " + result);
        System.out.println("expected: " + expected);
        assertEquals(result, expected);
    }
}
