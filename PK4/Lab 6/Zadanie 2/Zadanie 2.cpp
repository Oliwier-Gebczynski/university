#include <iostream>
#include <thread>
#include <mutex>
#include <vector>

std::vector<int> container;
std::mutex mutex_controler;

void add() {
    for (int i = 0; i < 1000000; i++) {
        std::lock_guard guard(mutex_controler);
        container.push_back(i);
    }
}

void sub() {
    for (int i = 0; i < 500000; i++) {
        std::lock_guard guard(mutex_controler);
        container.pop_back();
    }
}

int main() {
    std::thread adder1(add);
    std::thread adder2(add);

    adder1.join();
    adder2.join();

    std::thread sub1(sub);
    std::thread sub2(sub);

    sub1.join();
    sub2.join();

    std::cout << container.size() << std::endl;
    return 0;
}