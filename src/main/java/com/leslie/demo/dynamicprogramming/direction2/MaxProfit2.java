package com.leslie.demo.dynamicprogramming.direction2;

// too hard
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
public class MaxProfit2 {
    public int maxProfit(int[] prices, int fee) {
        int hold = -prices[0];  // failed, hold is the max profit we can get if we hold the stock at day i
        int cash = 0; // failed, cash is the max profit we can get if we don't hold the stock at day i

        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];

            int prevHold = hold;
            int prevCash = cash;

            hold = Math.max(prevHold, prevCash - price);
            cash = Math.max(prevCash, prevHold + price - fee); // failed, we need to sell the stock and pay the fee
        }

        return cash;
    }
}