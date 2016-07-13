package leetcode.hard;

/**
 47. Coins in a Line
 Code it now: Coming soon! Difficulty: Hard, Frequency: N/A

 Question:
 There are n coins in a line. (Assume n is even). Two players take turns to take a coin from either ends of the line
 until there are no more coins left. The player with the larger amount of money wins.

 1. Would you rather go first or second? Does it matter?
 2. Assume that you go first, describe an algorithm to compute the maximum amount of money you can win.

 Hints:
 If you go first, is there a strategy you can follow which prevents you from losing? Try to consider how it matters when
 the number of coins is odd vs. even.
 ------------------------------------------------------------------------------------------------------------------------
 Approach:
    1. Consider coins from {Ai.....Aj} and you can either choose Ai or choose Aj. Depending of those we get 2 cases: P1 and P2
    2. Assume, you take Ai : Remaining coins {Ai+1,....Aj}
        a. Look 1 step ahead -
            Opponent chooses Ai+1 : so remaining coins are {Ai+2, ....Aj}  denoted by P(i+2, j)
            Opponent chooses Aj : so remaining coins are {Ai+1,..... Aj-1} denoted by P(i+1, j-1)
            And opponent is equally smart, so he chooses the choice that yeilds minimum amount to you. (So MIN)
        So, P1 = Ai + MIN (P(i+2, j), P(i+1, j-1))

     3. Assume, you take Aj : Remaining coins {Ai,....Aj-1}
        a. Look 1 step ahead -
             Opponent chooses Ai : so remaining coins are {Ai+1, ....Aj-1}  denoted by P(i+1, j-1)
             Opponent chooses Aj-1 : so remaining coins are {Ai,..... Aj-2} denoted by P(i, j-2)
             And opponent is equally smart, so he chooses the choice that yeilds minimum amount to you. (So MIN)
        So, P2 = Aj + MIN (P(i, j-2), P(i+1, j-1))

    4. We want Maximum amount to Win,
       So P(i,j) = MAX (P1, P2)

 */
public class CoinsInLine {

    public static void main(String[] args) {

        CoinsInLine cil = new CoinsInLine();

        int[] coins = {3,2,2,3,1,2};
        int maxMoney = cil.maxMoney_DP(coins, coins.length);

        System.out.println("Max money = "+maxMoney);
    }


    int maxMoney_DP(int A[], int N) {
        int[][] T = new int[N][N];
        int a;                              //P(i+2, j)
        int b;                              //P(i+1, j-1)
        int c;                              //P(i, j - 2)

        for (int i = 0; i < N; i++) {
            for (int m = 0, n = i; n < N; m++, n++) {

                //Coming up with these recurrence relation is the KEY
                a = ((m+2 <= N-1) ? T[m+2][n] : 0);
                b = ((m+1 <= N-1 && n-1 >= 0) ? T[m+1][n-1] : 0);
                c = ((n-2 >= 0) ? T[m][n-2] : 0);

                int P1 = A[m] + Math.min(a,b);
                int P2 = A[n] + Math.min(b,c);

                T[m][n] = Math.max( P1, P2 );
            }
        }
        printMoves(T, A, N);
        return T[0][N-1];
    }

    void printMoves(int T[][], int A[], int N) {
        int m = 0, n = N-1;
        boolean myTurn = true;

        while (m <= n) {
            int P1 = T[m+1][n]; // If take A[m], opponent can get...
            int P2 = T[m][n-1]; // If take A[n]
            System.out.print(myTurn ? "I take coin no. " : "You take coin no. ");
            if (P1 <= P2) {
                System.out.print(m+1 +" ("+  A[m] + ")");
                m++;
            } else {
                System.out.print(n+1 + " (" + A[n] + ")");
                n--;
            }
            System.out.print(myTurn ? ", " : ".\n");
            myTurn = !myTurn;
        }
        System.out.println("\nThe total amount of money (maximum) I get is " + T[0][N-1] + ".\n");
    }
}
