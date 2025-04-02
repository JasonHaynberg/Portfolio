#ifndef PORTFOLIO_HPP
#define PORTFOLIO_HPP

#include <unordered_map>
#include <string>
#include <iostream>

class Portfolio {
public:
    std::unordered_map<std::string, int> ownedStocks;  // Stock name -> Quantity owned

    void buyStock(const std::string &stockName, int quantity) {
        ownedStocks[stockName] += quantity;
    }

    void sellStock(const std::string &stockName, int quantity) {
        if (ownedStocks.find(stockName) != ownedStocks.end() && ownedStocks[stockName] >= quantity) {
            ownedStocks[stockName] -= quantity;
            if (ownedStocks[stockName] == 0) {
                ownedStocks.erase(stockName);
            }
        } else {
            std::cout << "Error: Not enough stock to sell." << std::endl;
        }
    }

    void displayPortfolio() const {
        if (ownedStocks.empty()) {
            std::cout << "Your portfolio is empty!" << std::endl;
            return;
        }
        std::cout << "Your Portfolio:" << std::endl;
        for (const auto &entry : ownedStocks) {
            std::cout << entry.first << ": " << entry.second << " shares" << std::endl;
        }
    }
};

#endif // PORTFOLIO_HPP
