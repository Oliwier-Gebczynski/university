#include <iostream>
#include <thread>

void printMessageAndCalculatePowers(const std::string& message, int number) {
    std::cout << message << std::endl;

    int result = number;

    for (int i = 1; i < 5; i++) {
        std::cout << number << "^" << i << " = " << result << std::endl;
        result *= number;
    }
}

int main() {
    std::thread thread1(printMessageAndCalculatePowers, "Calculations performed by thread 1: ", 2);
    thread1.join();

    std::thread thread2(printMessageAndCalculatePowers, "Calculations performed by thread 2: ", 3);
    thread2.join();
    
    return 0;
}