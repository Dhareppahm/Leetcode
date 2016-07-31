package leetcode.medium;

/**
 https://leetcode.com/problems/surrounded-regions/
 130. Surrounded Regions

 Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

 A region is captured by flipping all 'O's into 'X's in that surrounded region.

 For example,
 X X X X
 X O O X
 X X O X
 X O X X
 After running your function, the board should be:

 X X X X
 X X X X
 X X X X
 X O X X

 */
public class SurroundedRegions {

    public static void main(String[] args) {
        SurroundedRegions sr = new SurroundedRegions();
        char[][] board = {
                {'X'}
//                {'X', 'X', 'X', 'X'}
//                {'X', 'O', 'O', 'X'},
//                {'X', 'X', 'O', 'X'},
//                {'X', 'O', 'X', 'X'}
        };
        System.out.println("Original Board: ");
        print(board);
        sr.solve(board);
        System.out.println("Captured Board: ");
        print(board);
    }

    private static void print(char[][] board) {
        for(int i=0; i < board.length; i++){
            for(int j=0; j < board[0].length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     The idea is pretty simple: a 'O' marked cell cannot be captured (converted to X) when:

     1. It is in on the border of the board or
     2. It is adjacent to an unflippable cell (U).

     So the algorithm is straightforward:

     1. Go around the border of the board
        When a 'O' cell is found mark it with 'U' and perform a DFS on its adjacent cells looking for other 'O' marked cells.

     2. Postprocessing step: When the entire border is processed scan again the board
         If a cell is marked as 'O' it wasn't connected to unflippable cell. Hence capture/convert it to 'X'
         If a cell is marked as 'X' nothing must be done.
         If a cell is marked as 'U' mark it as 'O' because it was an original 'O' marked cell which satisfied one of the above conditions.
     */
    public void solve(char[][] board) {

        if(board.length == 0)
            return;

        int rows = board.length;
        int cols = board[0].length;

        //first row, col & last row, col O's are excluded, as they will never be surrounded by 'X'

        //Turn first and last COLUMNS O's to U
        for(int i = 0; i < rows; i++){
            if(board[i][0] == 'O')          //1st column
                DFS(i, 0, board);

            if(board[i][cols-1] == 'O')     //last column
                DFS(i, cols-1, board);
        }

        //Turn first and last ROWS O's to U
        for(int i = 0; i < cols; i++){
            if(board[0][i] == 'O')          //1st row
                DFS(0, i, board);

            if(board[rows-1][i] == 'O')     //last row
                DFS(rows-1, i, board);
        }

        //Post processing step:
        // Convert U's to O as they couldn't be flipped/converted
        // Convert O's to X's
        // Keep X's as it is to X
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(board[i][j] == 'U') {
                    board[i][j] = 'O';
                }else {
                    board[i][j] = 'X';
                }
            }
        }

    }


    //Core Logic
    private void DFS(int row, int col, char[][] board){

        if(board[row][col] == 'X' || board[row][col] == 'U')
            return;

        if(board[row][col] == 'O')          //if its unflippable cell, mark it as U, so in end they're kept as O and not converted
            board[row][col] = 'U';

        //Recursively perform a DFS on its adjacent cells looking for other 'O' marked cells
        //Check before and after rows
        if(row-1 > 0)
            DFS(row-1, col, board);

        if(row+1 < board.length)
            DFS(row+1, col, board);

        //Check before and after cols
        if(col-1 > 0)
            DFS(row, col-1, board);

        if(col+1 < board[0].length)
            DFS(row, col+1, board);

   }

}
