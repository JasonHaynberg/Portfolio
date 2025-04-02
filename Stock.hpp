#ifndef STOCK_HPP
#define STOCK_HPP

#include <iostream>
#include <string>
#include "httplib.h" // For making HTTP requests
#include "json.hpp"   // For parsing JSON responses

// Define a shortcut for nlohmann::json
using json = nlohmann::json;

class Stock {
public:
    std::string name;  // Stock name (symbol)
    double price;      // Stock price

    // Constructor
    Stock(std::string n) : name(n), price(0.0) {}

    // Method to update the stock's price by fetching from Alpha Vantage API
    void updatePrice();

    // Method to display stock info
    void display() const {
        std::cout << "Stock Name: " << name << ", Current Price: " << price << std::endl;
    }

private:
    // Fetch real-time stock data from Alpha Vantage
    double getStockPrice(const std::string& stockSymbol);
};

#endif // STOCK_HPP
