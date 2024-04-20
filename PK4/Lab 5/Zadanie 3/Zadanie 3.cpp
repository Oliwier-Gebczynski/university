#include <iostream>
#include <thread>
#include <future>

void current(std::promise<float>& promise, float Q, float t) {
    float I = Q / t;
    promise.set_value(I);
    std::cout << "Current is equal: " << I << std::endl;
}

void resistance(std::promise<float>& promise, float R1, float R2) {
    float R = R1 * R2 / (R1 + R2);
    promise.set_value(R);
    std::cout << "Equivalent resistance is equal: " << R << std::endl;
}

int main() {
    std::promise<float> promise_current;
    std::promise<float> promise_resistance;

    std::future<float> future_current = promise_current.get_future();
    std::future<float> future_resistance = promise_resistance.get_future();

    std::thread t1(current, std::ref(promise_current), 8, 10);
    t1.join();

    std::thread t2(resistance, std::ref(promise_resistance), 5, 13);
    float U = future_current.get() * future_resistance.get();
    t2.join();

    std::cout << "Voltage is equal: " << U << std::endl;

    return 0;
}


