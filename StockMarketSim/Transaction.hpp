#ifndef TRANSACTION_HPP
#define TRANSACTION_HPP

#include <iostream>
#include <string>
#include <ctime>

using namespace std;

// A class to hold details of a transaction (buy or sell)
class Transaction {
public:
    string type;        // Buy or Sell
    string stockName;   // Stock symbol
    int quantity;       // Quantity of stocks involved in the transaction
    double price;       // Price at which the stock was bought/sold
    time_t timestamp;   // Time the transaction occurred

    // Constructor to initialize a transaction
    Transaction(string t, string stock, int qty, double p)
        : type(t), stockName(stock), quantity(qty), price(p) {
        timestamp = time(0);  // Set the current time
    }

    // Display transaction details
    void display() const {
        cout << "Type: " << type << "\n"
             << "Stock: " << stockName << "\n"
             << "Quantity: " << quantity << "\n"
             << "Price: $" << price << "\n"
             << "Timestamp: " << ctime(&timestamp) << endl;
    }
};

#endif // TRANSACTION_HPP
