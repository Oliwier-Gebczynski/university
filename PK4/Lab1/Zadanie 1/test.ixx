export module test;

export import :test2;
export import :test1;

import <iostream>;

export void hello() {
    std::cout << "Hello!" << std::endl;
}
