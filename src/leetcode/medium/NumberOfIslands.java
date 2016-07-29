package leetcode.medium;

/**
 https://leetcode.com/problems/number-of-islands/
 200. Number of Islands

 Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is
 formed by connecting adjacent lands horizontally or vertically. (so only 4 adjacent nodes)

 You may assume all four edges of the grid are all surrounded by water.

 Example 1:

 11110
 11010
 11000
 00000
 Answer: 1

 Example 2:

 11000
 11000
 00100
 00011
 Answer: 3
------------------------------------------------------------------------------------------------------------------------
 Approach:
    Consider this grid a graph (adjacency matrix representation)
    DFS for 4 adjacent nodes recursively
    count = no. of times DFS called
 */
public class NumberOfIslands {

    public static void main(String[] args) {
        NumberOfIslands obj = new NumberOfIslands();

        char[][] grid = {};/*{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };*/
        int count = obj.numIslands(grid);
        System.out.println("No. of Islands = "+count);

    }

    /**
     * Consider this grid a graph (adjacency matrix representation) and do DFS for 4 adjacent nodes recursively
     *
     */
    public int numIslands(char[][] grid) {
        if(grid.length == 0){
            return 0;
        }

        int row = grid.length;
        int col = grid[0].length;

        //Keep track of visited nodes
        boolean[][] visited = new boolean[row][col];

        int count = 0;
        for(int i=0; i < row; i++){
            for(int j=0; j < col; j++){
                if(grid[i][j] == '1' && !visited[i][j]){      //if land(1) and not visited
                    DFS(i, j, grid, visited);
                    count++;                                //count = no. of times DFS called
                }
            }
        }

        return count;
    }

    private void DFS(int row, int col, char[][] grid, boolean[][] visited){
        int ROWS = grid.length;
        int COLS = grid[0].length;

        visited[row][col] = true;

        //check 4 adjacent nodes
        //If 1 and not visited, recursively call DFS
        if(row - 1 >= 0 && grid[row - 1][col] == '1' && !visited[row - 1][col])
            DFS(row - 1, col, grid, visited);

        if(row + 1 < ROWS && grid[row + 1][col] == '1' && !visited[row + 1][col])
            DFS(row + 1, col, grid, visited);

        if(col - 1 >= 0 && grid[row][col - 1] == '1' && !visited[row][col - 1])
            DFS(row, col - 1, grid, visited);

        if(col + 1 < COLS && grid[row][col + 1] == '1' && !visited[row][col + 1])
            DFS(row, col + 1, grid, visited);
    }

}
