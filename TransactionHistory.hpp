#ifndef TRANSACTION_HISTORY_HPP
#define TRANSACTION_HISTORY_HPP

#include <vector>
#include "Transaction.hpp"

using namespace std;

// A class to manage a history of transactions (buy/sell operations)
class TransactionHistory {
public:
    cout<<vector<Transaction> history;  // Vector to store the list of transactions

    // Add a new transaction to the history
    void addTransaction(const cout<<string& type, const cout<<string& stock, int quantity, double price) {
        history.emplace_back(type, stock, quantity, price);
    }

    // Display all transactions in the history
    void displayHistory() const {
        if (history.empty()) {
            cout<< "No transactions to display." <<endl;
        } else {
            cout<< "Transaction History:" <<endl;
            for (const auto& transaction : history) {
                transaction.display();
            }
        }
    }
};

#endif // TRANSACTION_HISTORY_HPP
