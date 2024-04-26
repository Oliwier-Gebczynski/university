#include <iostream>
#include <thread>
#include <atomic>
#include <mutex>
#include <condition_variable>

std::atomic<int> counter(0);

std::mutex mutex_controler;
std::condition_variable condition;
bool ready = false;
bool processed = false;

void add() {
    for (int i = 0; i < 1000000; i++) {
        counter.fetch_add(1);
    }
}

void sub() {
    std::unique_lock<std::mutex> lock(mutex_controler);

    condition.wait(lock, []() { return ready; });

    std::cout << "Processing data..." << std::endl;

    for (int i = 0; i < 100000; ++i) {
        counter = counter - 1;
    }

    std::cout << "Processed data!" << std::endl;

    processed = true;
    condition.notify_one();

    lock.unlock();
}

int main()
{
    std::cout << "Counter before: " << counter << std::endl;
    std::thread n1(add);
    std::thread n2(add);
    std::thread n3(sub);
    n1.join();
    n2.join();

    std::cout << "Counter after addition: " << counter << std::endl;

    {
        std::lock_guard lock(mutex_controler);
        ready = true;
        std::cout << "Ready for processing..." << std::endl;
    }

    condition.notify_one();

    {
        std::unique_lock lock(mutex_controler);
        condition.wait(lock, [] { return processed; });
    }

    std::cout << "Result = " << counter << std::endl;

    n3.join();
    
}
