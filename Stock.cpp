#include "Stock.hpp"
#include <iostream>
#include <string>
#include <stdexcept>

// Define the API key and base URL (from Alpha Vantage)
const std::string API_KEY = "XHE3HYQNZ4CDBJI6";  // Replace with your API key

// Fetch real-time stock data
double Stock::getStockPrice(const std::string& stockSymbol) {
    httplib::Client cli("https://www.alphavantage.co");

    // Build the API request URL for the "TIME_SERIES_INTRADAY" endpoint
    std::string url = "/query?function=TIME_SERIES_INTRADAY&symbol=" + stockSymbol + "&interval=5min&apikey=" + API_KEY;
    
    auto res = cli.Get(url.c_str());
    if (res && res->status == 200) {
        try {
            // Parse the response body as JSON
            json data = json::parse(res->body);

            // Check if the response contains the correct data
            if (data.contains("Time Series (5min)")) {
                // Get the most recent data (latest available timestamp)
                auto time_series = data["Time Series (5min)"];
                auto latest_time = time_series.begin();
                double price = std::stod(latest_time.value()["4. close"].get<std::string>());
                return price;
            } else {
                std::cerr << "Error: Invalid stock symbol or API limit reached." << std::endl;
            }
        } catch (const std::exception& e) {
            std::cerr << "Error parsing stock data: " << e.what() << std::endl;
        }
    } else {
        std::cerr << "Error fetching data from Alpha Vantage API." << std::endl;
    }
    return 0.0;
}

// Update stock price using the fetched data from the API
void Stock::updatePrice() {
    price = getStockPrice(name);
}
