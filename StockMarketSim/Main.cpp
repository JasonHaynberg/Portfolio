#include <iostream>
#include <string>
#include "Stock.hpp"
#include "Portfolio.hpp"
#include "TransactionHistory.hpp"
#include "StockMarket.hpp"

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

int main() {
    StockMarket market;
    Portfolio userPortfolio;

    // Add sample stocks (the symbol is used to update prices later)
    market.addStock("AAPL");
    market.addStock("GOOG");
    market.addStock("TSLA");

    int choice;
    bool running = true;

    while (running) {
        cout << "\nStock Market Simulator" << endl;
        cout << "1. View Available Stocks" << endl;
        cout << "2. Buy Stock" << endl;
        cout << "3. Sell Stock" << endl;
        cout << "4. View Portfolio" << endl;
        cout << "5. View Transaction History" << endl;
        cout << "6. Exit" << endl;
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                market.displayStocks();
                break;
            case 2: {
                string stockName;
                int quantity;
                cout << "Enter stock name: ";
                cin >> stockName;
                cout << "Enter quantity to buy: ";
                cin >> quantity;
                if (market.availableStocks.find(stockName) != market.availableStocks.end()) {
                    double price = market.availableStocks[stockName].price;
                    market.recordTransaction("Buy", stockName, quantity, price);
                    userPortfolio.buyStock(stockName, quantity);
                    cout << "Bought " << quantity << " shares of " << stockName << endl;
                } else {
                    cout << "Stock not available!" << endl;
                }
                break;
            }
            case 3: {
                string stockName;
                int quantity;
                cout << "Enter stock name: ";
                cin >> stockName;
                cout << "Enter quantity to sell: ";
                cin >> quantity;
                if (market.availableStocks.find(stockName) != market.availableStocks.end()) {
                    double price = market.availableStocks[stockName].price;
                    market.recordTransaction("Sell", stockName, quantity, price);
                    userPortfolio.sellStock(stockName, quantity);
                    cout << "Sold " << quantity << " shares of " << stockName << endl;
                } else {
                    cout << "Stock not available!" << endl;
                }
                break;
            }
            case 5:
                market.transactionHistory.displayHistory();
                break;
            case 6:
                running = false;
                cout << "Exiting simulator." << endl;
                break;
            default:
                cout << "Invalid choice! Please try again." << endl;
                break;
        }
    }

    return 0;
}
