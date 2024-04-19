#include <iostream>
#include <future>
#include <functional>

int main() {
    int a = 13;
    int b = 1;

    double c = 3.14;
    double d = 1.61;

    std::future<int> intSumFuture = std::async([](int a, int b) { return a + b; }, a, b);
    int intSum = intSumFuture.get();

    std::future<double> doubleSumFuture = std::async([](double a, double b) { return a + b; }, c, d);
    double doubleSum = doubleSumFuture.get();

    std::cout << "Integer sum: " << intSum << std::endl;
    std::cout << "Double sum: " << doubleSum << std::endl;

    return 0;
}

