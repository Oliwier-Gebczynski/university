#include <iostream>
#include <thread>
#include <mutex>
#include <vector>

std::vector<int> data;
std::mutex mutex_controler;
int out = 1;

void prod() {
    while (true) {
        std::string x;
        std::cin >> x;
        if (x == "KONIEC") {
            break;
        }
        std::lock_guard guard(mutex_controler);

        data.push_back(std::stoi(x));
        std::this_thread::yield();
    }
}

void cons() {
    while (true) {
        std::lock_guard guard(mutex_controler);
        if (data.empty()) {
            std::this_thread::yield();
            continue;
        }
        out *= data.back();
        data.pop_back();
    }
}

int main() {
    std::thread producer(prod);
    std::thread consument(cons);

    producer.join();
    consument.detach();

    std::cout << "Result: " << out;

    return 0;
}