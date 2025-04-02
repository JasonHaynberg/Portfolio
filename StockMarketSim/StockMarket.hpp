#ifndef STOCKMARKET_HPP
#define STOCKMARKET_HPP

#include <iostream>
#include <string>
using namespace std;

class StockMarket {
public:
    unordered_map<string, Stock> availableStocks;  // Stock symbol -> Stock object
    TransactionHistory transactionHistory;          // To record all transactions

    // Add a new stock to the market
    void addStock(const string& stockSymbol) {
        availableStocks[stockSymbol] = Stock(stockSymbol);
    }

    // Display all available stocks in the market
    void displayStocks() const {
        if (availableStocks.empty()) {
            cout << "No stocks available." << endl;
            return;
        }
        cout << "Available Stocks:" << endl;
        for (const auto& stockEntry : availableStocks) {
            stockEntry.second.display();
        }
    }

    // Record a transaction (buy/sell) in the transaction history
    void recordTransaction(const string& type, const string& stockSymbol, int quantity, double price) {
        transactionHistory.addTransaction(type, stockSymbol, quantity, price);
    }
};
#endif // STOCKMARKET_HPP