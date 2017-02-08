package leetcode.easy;

/*
121. Best Time to Buy and Sell Stock
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.


*/
public class BestTimeBuySellStock{

	public static void main(String[] args){
		BestTimeBuySellStock obj = new BestTimeBuySellStock();
		int[] prices =  {2,1,2,1,0,1,2};
						//{7, 6, 4, 3, 1};
						// {7, 1, 5, 3, 6, 4};
						// {7,2,5,3,6,4,3,6,7,7,3,9,7,4,2,1};
		int maxProfit = obj.maxProfit(prices);
		System.out.println(maxProfit);
	}

	/*
	We can maintain two variables: buy = minprice (minimum price to buy) and
	profit = maxprofit corresponding to (maximum difference between selling price and minprice) obtained so far respectively.
	*/
	public int maxProfit(int[] prices) {
		if(prices.length == 0)
			return 0;

		int minPrice = prices[0];
		int profit = 0;

		for(int i=1; i < prices.length; i++){
			if(prices[i] < minPrice){								//buy at min price
				minPrice = prices[i];
			}

			if(profit < prices[i] - minPrice){						//sell at max price, so check if more profit is found
				profit = prices[i] - minPrice;
			}
		}	

        return profit;
    }

}