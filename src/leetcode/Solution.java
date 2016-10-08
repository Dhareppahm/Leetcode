package leetcode;

/**
 * Created by sandeepkulkarni on 9/26/16.
 */
public class Solution {

    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] stocks = //{2,1,2,1,0,1,2};
        {7, 6, 4, 3, 1};
        // {7, 1, 5, 3, 6, 4};
         //{7,2,5,3,6,4,3,6,7,7,3,9,7,4,2,1};
        int profit = obj.getMaxProfit(stocks);
        System.out.println(profit);
    }

    private int getMaxProfit(int[] stocks){

        int buy = stocks[0];
        int profit = 0;

        for(int i=1; i < stocks.length; i++){
            if(stocks[i] < buy){
                buy = stocks[i];
            }

            if(stocks[i] - buy > profit){
                profit = stocks[i] - buy;
            }
        }

        return profit;
    }


}
