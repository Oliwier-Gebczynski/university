#include <vector>
#include <future>
#include <thread>
#include <iostream>

int addition(int a, int b) {
    return a + b;
}

void assign_tasks(std::vector<std::packaged_task<int(int, int)>>& tasks, std::vector<std::future<int>>& results) {
    for (int i = 0; i < tasks.size(); ++i) {
        tasks[i] = std::packaged_task<int(int, int)>(std::move(addition));
        results[i] = tasks[i].get_future();
    }
}

void launch_tasks(std::vector<std::packaged_task<int(int, int)>>& tasks, std::vector<std::future<int>>& results) {
    for (int i = 0; i < tasks.size(); ++i) {
        tasks[i](i + 1, i + 2);
    }
}

int main() {
    std::vector<std::packaged_task<int(int, int)>> tasks(5);
    std::vector<std::future<int>> results(5);
    std::vector<std::thread> threads;

    std::thread t1(assign_tasks, std::ref(tasks), std::ref(results));
    std::thread t2(launch_tasks, std::ref(tasks), std::ref(results));

    t1.join();
    t2.join();

    for (int i = 0; i < results.size(); ++i) {
        std::cout << "Result " << i + 1 << ": " << results[i].get() << std::endl;
    }

    return 0;
}