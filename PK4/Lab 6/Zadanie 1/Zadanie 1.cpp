#include <iostream>
#include <thread>
#include <mutex>

int i = 0;

void timer() {
    while (true) {
        std::cout << i++ << std::endl;
        std::this_thread::sleep_for(std::chrono::milliseconds(1000));
    }
}

int main() {
    std::thread t1(timer);

    t1.join();
    t1.detach();
    //Gdy jest sam .join() to nie mozna przerwac dzialania programu za pomoca ctrl+c, a z .detach() jest to mozliwe
    return 0;
}